package dev.trustproject.vocdoni;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import dvote.types.v1.Vochain;
import dvote.types.v1.Vochain.*;
import dev.trustproject.vocdoni.configuration.VocdoniProperties;
import dev.trustproject.vocdoni.model.account.FaucetPackage;
import dev.trustproject.vocdoni.model.account.*;
import dev.trustproject.vocdoni.model.census.CensusProof;
import dev.trustproject.vocdoni.model.census.PublishedCensusInfo;
import dev.trustproject.vocdoni.model.chain.ChainSubmitTxResponse;
import dev.trustproject.vocdoni.model.chain.TransactionInfo;
import dev.trustproject.vocdoni.model.chain.VochainInfo;
import dev.trustproject.vocdoni.model.process.*;
import io.vocdoni.api.AccountsApi;
import io.vocdoni.invoker.ApiClient;
import io.vocdoni.invoker.ApiException;
import io.vocdoni.invoker.Configuration;
import io.vocdoni.model.AccountsPostRequest;
import io.vocdoni.model.ApiAccount;
import io.vocdoni.model.ApiAccountSet;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.*;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;

import static dev.trustproject.vocdoni.VocdoniConstants.*;
import static dev.trustproject.vocdoni.VocdoniUtils.*;

@Component
public class VocdoniClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final VocdoniProperties config;

    private final TransactionSigner transactionSigner;

    private final AccountsApi accountsApi;

    public VocdoniClient(VocdoniProperties config, TransactionSigner transactionSigner) {
        this.config = config;
        this.transactionSigner = transactionSigner;

        final ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(config.getApiUrl());

        this.accountsApi = new AccountsApi(defaultClient);
    }

    public String signTransaction(byte[] tx, String walletAddress) {
        return transactionSigner.signTransaction(tx, walletAddress);
    }

    private String getCID(String payload) throws JsonProcessingException {
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", payload);
        String jsonString = objectMapper.writeValueAsString(payloadMap);

        HttpEntity<String> httpEntity = new HttpEntity<>(jsonString, makeHeaders());

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            config.getApiUrl() + CID,
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

        return response.getBody().get("cid") != null
            ? (String) response.getBody().get("cid")
            : null;
    }

    private CensusProof getCensusProof(String voterAddress, String censusId, String censusToken) {
        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(censusToken);

        ResponseEntity<CensusProof> response = restTemplate.exchange(
            config.getApiUrl() + CENSUSES + "/" + censusId + PROOF + "/" + voterAddress,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            CensusProof.class);

        return response.getBody();
    }

    public FaucetPackage getFaucet(String walletAddress) throws JsonProcessingException {
        HttpHeaders headers = makeHeaders();

        ResponseEntity<FaucetPackageInfo> response = restTemplate.exchange(
            config.getFaucetUrl() + "/" + walletAddress,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            FaucetPackageInfo.class);

        FaucetPackageInfo faucetPackageInfo = response.getBody();

        byte[] faucetPackageBytes  = Base64.getDecoder().decode(faucetPackageInfo.getFaucetPackage());

        JsonNode jsonNode = objectMapper.readTree(new String(faucetPackageBytes));
        String payload = jsonNode.get("faucetPayload").asText();
        String signature = jsonNode.get("signature").asText();

        return new FaucetPackage(payload, signature);
    }

    public void collectTokens(String walletAddress) throws JsonProcessingException, ApiException {
        ApiAccount accountInfo = getAccountInfo(walletAddress);
        FaucetPackage faucetPackage = getFaucet(walletAddress);

        byte[] payloadBytes = Base64.getDecoder().decode(faucetPackage.getPayload().getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = Base64.getDecoder().decode(faucetPackage.getSignature().getBytes(StandardCharsets.UTF_8));

        Vochain.FaucetPackage vochainFaucetPackage = Vochain.FaucetPackage.newBuilder()
            .setPayload(ByteString.copyFrom(payloadBytes))
            .setSignature(ByteString.copyFrom(signatureBytes))
            .build();

        CollectFaucetTx collectFaucetTx = CollectFaucetTx.newBuilder()
            .setNonce(accountInfo.getNonce())
            .setFaucetPackage(vochainFaucetPackage)
            .build();

        Tx tx = Tx.newBuilder()
            .setCollectFaucet(collectFaucetTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, walletAddress);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(payloadJson, headers);

        ResponseEntity<ChainSubmitTxResponse> response = restTemplate.exchange(
            config.getApiUrl() + CHAIN_TRANSACTION,
            HttpMethod.POST,
            httpEntity,
            ChainSubmitTxResponse.class);

        ChainSubmitTxResponse chainSubmitTxResponse = response.getBody();

        waitForTransaction(chainSubmitTxResponse.getHash());
    }

    public ApiAccountSet createAccount(String walletAddress, FaucetPackage faucetPackage, AccountMetadata accountMetadata) throws JsonProcessingException, ApiException {
        String accountMetadataJson = objectMapper.writeValueAsString(accountMetadata);
        String metadata = Base64.getEncoder().encodeToString(accountMetadataJson.getBytes(StandardCharsets.ISO_8859_1));

        String cid = getCID(metadata);

        Vochain.FaucetPackage faucetPackageProto = Vochain.FaucetPackage.newBuilder()
            .setPayload(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.getPayload())))
            .setSignature(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.getSignature())))
            .build();

        SetAccountTx setAccountTx = SetAccountTx.newBuilder()
            .setTxtype(TxType.CREATE_ACCOUNT)
            .setNonce(1)
            .setInfoURI(cid)
            .setAccount(ByteString.fromHex(strip0x(walletAddress)))
            .setFaucetPackage(faucetPackageProto)
            .addAllDelegates(new ArrayList<>())
            .build();

        Tx tx = Tx.newBuilder()
            .setSetAccount(setAccountTx)
            .build();

        String signedTx = signTransaction(tx.toByteArray(), walletAddress);

        return this.accountsApi.accountsPost(new AccountsPostRequest().txPayload(signedTx).metadata(metadata));
    }

    public ApiAccount getAccountInfo(String walletAddress) throws ApiException {
        return this.accountsApi.accountsAddressGet(walletAddress);
    }

    public List<Election> getElectionList(String walletAddress, int page) {
        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            config.getApiUrl() + ACCOUNTS + "/" + walletAddress + ELECTIONS + PAGE + "/" + page,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

        return response.getBody().get("elections") != null
            ? (List<Election>) response.getBody().get("elections")
            : null;
    }

    public String createCensus(String token) throws JsonProcessingException {
        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            config.getApiUrl() + CENSUSES + WEIGHTED,
            HttpMethod.POST,
            httpEntity,
            String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        return jsonNode.get("censusID").asText();
    }

    public void addParticipantsToCensus(String censusId, String token, List<CensusParticipant> participants) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        Map<String, List<CensusParticipant>> requestBody = new HashMap<>();
        requestBody.put("participants", participants);
        HttpEntity<Map<String, List<CensusParticipant>>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
            config.getApiUrl() + CENSUSES + "/" + censusId + PARTICIPANTS,
            HttpMethod.POST,
            httpEntity,
            Void.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Request failed with status code: " + response.getStatusCode());
        }
    }

    public PublishedCensusInfo publishCensus(String censusId, String token) {
        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<PublishedCensusInfo> response = restTemplate.exchange(
            config.getApiUrl() + CENSUSES + "/" + censusId + PUBLISH,
            HttpMethod.POST,
            httpEntity,
            PublishedCensusInfo.class);

        return response.getBody();
    }

    public VochainInfo getVochainInfo() {
        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<VochainInfo> response = restTemplate.exchange(
            config.getApiUrl() + CHAIN_INFO,
            HttpMethod.GET,
            httpEntity,
            VochainInfo.class);

        return response.getBody();
    }

    public ProcessInfo getProcessInfo(String electionId) {
        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<ProcessInfo> response = restTemplate.exchange(
            config.getApiUrl() + ELECTIONS + "/" + electionId,
            HttpMethod.GET,
            httpEntity,
            ProcessInfo.class);

        return response.getBody();
    }

    public ProcessInfo createProcess(
            String walletAddress,
            String title,
            String description,
            ProcessMedia media,
            Map<String, Object> meta,
            Instant startDate,
            Instant endDate,
            String censusId,
            String censusURI,
            List<VocdoniQuestion> questions,
            int censusSize
    ) throws JsonProcessingException, ApiException {
        EnvelopeType envelopeType = EnvelopeType.newBuilder()
                .setSerial(false)
                .setAnonymous(false)
                .setEncryptedVotes(false)
                .setUniqueValues(false)
                .setCostFromWeight(false)
                .build();

        ProcessMode mode = ProcessMode.newBuilder()
                .setAutoStart(true)
                .setInterruptible(true)
                .setDynamicCensus(false)
                .setEncryptedMetaData(false)
                .setPreRegister(false)
                .build();

        ProcessVoteOptions voteOptions = ProcessVoteOptions.newBuilder()
                .setMaxCount(questions.size())
                .setMaxValue(questions.stream().mapToInt(question -> question.getChoices().size()).max().getAsInt())
                .setMaxVoteOverwrites(1)
                .setCostExponent(1)
                .build();

        return createProcess(walletAddress, title, description, media, meta, startDate, endDate, censusId, censusURI, questions, censusSize, envelopeType, mode, voteOptions);
    }

    public ProcessInfo createProcess(
        String walletAddress,
        String title,
        String description,
        ProcessMedia media,
        Map<String, Object> meta,
        Instant startDate,
        Instant endDate,
        String censusId,
        String censusURI,
        List<VocdoniQuestion> questions,
        int censusSize,
        EnvelopeType envelopeType,
        ProcessMode mode,
        ProcessVoteOptions voteOptions
    ) throws JsonProcessingException, ApiException {
        Map<String, String> titleMap = new HashMap<>();
        titleMap.put("default", title);
        Map<String, String> descriptionMap = new HashMap<>();
        descriptionMap.put("default", description);
        ProcessMetadata metadata = new ProcessMetadata(PROTOCOL_VERSION, titleMap, descriptionMap, media, meta, questions, null);
        String jsonString = objectMapper.writeValueAsString(metadata);

        String base64EncodedMetadata = Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.ISO_8859_1));
        String cid = getCID(base64EncodedMetadata);

        VochainInfo vochainInfo = getVochainInfo();
        Instant blockTimestamp = vochainInfo.getBlockTimestamp();
        int height = vochainInfo.getHeight();
        int[] blockTime = vochainInfo.getBlockTime();
        int startBlock = estimateBlockAtDateTime(startDate, blockTimestamp, height, blockTime);
        int endBlock = estimateBlockAtDateTime(endDate, blockTimestamp, height, blockTime);

        final ApiAccount accountInfo = this.getAccountInfo(walletAddress);

        Vochain.Process process = Vochain.Process.newBuilder()
            .setEntityId(ByteString.fromHex(strip0x(walletAddress)))
            .setStartBlock(startBlock)
            .setBlockCount(endBlock - startBlock)
            .setCensusRoot(ByteString.fromHex(strip0x(censusId)))
            .setCensusURI(censusURI)
            .setStatus(Vochain.ProcessStatus.READY)
            .setEnvelopeType(envelopeType)
            .setMode(mode)
            .setVoteOptions(voteOptions)
            .setCensusOrigin(CensusOrigin.OFF_CHAIN_TREE_WEIGHTED)
            .setMetadata(cid)
            .setMaxCensusSize(censusSize)
            .build();

        NewProcessTx newProcessTx = NewProcessTx.newBuilder()
            .setTxtype(TxType.NEW_PROCESS)
            .setNonce(accountInfo.getNonce())
            .setProcess(process)
            .build();

        Tx tx = Tx.newBuilder()
            .setNewProcess(newProcessTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, walletAddress);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("txPayload", signedTx);
        payloadMap.put("metadata", base64EncodedMetadata);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        ResponseEntity<NewProcess> response = restTemplate.exchange(
            config.getApiUrl() + ELECTIONS,
            HttpMethod.POST,
            new HttpEntity<>(payloadJson, headers),
            NewProcess.class);

        NewProcess newProcess = response.getBody();

        waitForTransaction(newProcess.getTxHash());

        return getProcessInfo(newProcess.getElectionID());
    }

    public void changeProcessStatus(String walletAddress, String electionId, VocdoniProcessStatus status) throws JsonProcessingException, ApiException {
        ApiAccount accountInfo = getAccountInfo(walletAddress);

        SetProcessTx setProcessTx = SetProcessTx.newBuilder()
            .setTxtype(TxType.SET_PROCESS_STATUS)
            .setNonce(accountInfo.getNonce())
            .setProcessId(ByteString.fromHex(strip0x(electionId)))
            .setStatus(Vochain.ProcessStatus.valueOf(status.toString()))
            .build();

        Tx tx = Tx.newBuilder()
            .setSetProcess(setProcessTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, walletAddress);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        restTemplate.exchange(
            config.getApiUrl() + CHAIN_TRANSACTION,
            HttpMethod.POST,
            new HttpEntity<>(payloadJson, headers),
            Void.class);
    }

    public String vote(String electionId, String walletAddress, String censusToken, List<Integer> votes) throws JsonProcessingException {
        ProcessInfo processInfo = getProcessInfo(electionId);
        String censusId = processInfo.getCensus().getCensusRoot();

        CensusProof censusProof = getCensusProof(walletAddress, censusId, censusToken);

        ProofArbo aProof = ProofArbo.newBuilder()
            .setSiblings(ByteString.fromHex(censusProof.getCensusProof()))
            .setType(ProofArbo.Type.BLAKE2B)
            .setAvailableWeight(ByteString.fromHex(censusProof.getValue()))
            .setKeyType(ProofArbo.KeyType.ADDRESS)
            .build();

        Proof proof = Proof.newBuilder()
            .setArbo(aProof)
            .build();

        SecureRandom random = new SecureRandom();
        byte[] voteEnvelopeNonceBytes = new byte[32];
        random.nextBytes(voteEnvelopeNonceBytes);
        byte[] voteEnvelopeNonce = Hash.sha3(voteEnvelopeNonceBytes);

        byte[] tempBytes = new byte[32];
        random.nextBytes(tempBytes);
        byte[] hashedTempBytes = Hash.sha3(tempBytes);
        byte[] votePackageNonceBytes = Arrays.copyOfRange(hashedTempBytes, 0, 8);
        StringBuilder sb = new StringBuilder();
        for (byte b : votePackageNonceBytes) {
            sb.append(String.format("%02x", b));
        }
        String votePackageNonce = sb.toString();

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("nonce", votePackageNonce);
        payloadMap.put("votes", votes);
        String strPayload = objectMapper.writeValueAsString(payloadMap);
        byte[] votePackage = strPayload.getBytes(StandardCharsets.UTF_8);

        VoteEnvelope voteEnvelope = VoteEnvelope.newBuilder()
            .setProof(proof)
            .setProcessId(ByteString.fromHex(strip0x(electionId)))
            .setNonce(ByteString.copyFrom(voteEnvelopeNonce))
            .setVotePackage(ByteString.copyFrom(votePackage))
            .addAllEncryptionKeyIndexes(Collections.emptyList())
            .setNullifier(ByteString.copyFrom(new byte[0]))
            .build();

        Tx tx = Tx.newBuilder()
            .setVote(voteEnvelope)
            .build();

        String signedTx = signTransaction(tx.toByteArray(), walletAddress);

        Map<String, String> payload = new HashMap<>();
        payload.put("txPayload", signedTx);
        String jsonPayload = objectMapper.writeValueAsString(payload);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<NewVote> response = restTemplate.exchange(
            config.getApiUrl() + VOTES,
            HttpMethod.POST,
            entity,
            NewVote.class);

        NewVote newVote = response.getBody();

        waitForTransaction(newVote.getTxHash());

        return newVote.getVoteID();
    }

    private void waitForTransaction(String txHash) {
        int attemptsNumber = ATTEMPTS;

        HttpHeaders headers = makeHeaders();

        while (attemptsNumber > 0) {

            try {
                ResponseEntity<TransactionInfo> response = restTemplate.exchange(
                        config.getApiUrl() + TRANSACTION_BY_HASH + "/" + txHash,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        TransactionInfo.class
                );

                if (response.getStatusCode() != HttpStatus.OK) {
                    throw new RuntimeException();
                }

                break;
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Request error: " + e.getMessage());
            } catch (Exception ignored) {
                // Do nothing, continue retrying
            }

            attemptsNumber--;

            if (attemptsNumber == 0) {
                throw new RuntimeException("Time out waiting for transaction: " + txHash);
            }

            try {
                Thread.sleep(RETRY_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public interface TransactionSigner {

        String signTransaction(byte[] tx, String walletAddress);

    }

}
