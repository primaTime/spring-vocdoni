package dev.trustproject.vocdoni;

import static dev.trustproject.vocdoni.Routes.*;
import static dev.trustproject.vocdoni.VocdoniConstants.*;
import static dev.trustproject.vocdoni.VocdoniUtils.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import dev.trustproject.vocdoni.configuration.VocdoniProperties;
import dev.trustproject.vocdoni.model.TxMessage;
import dev.trustproject.vocdoni.model.internal.*;
import dev.trustproject.vocdoni.model.payload.CensusParticipant;
import dev.trustproject.vocdoni.model.response.*;
import dev.trustproject.vocdoni.model.response.Census;
import dev.trustproject.vocdoni.model.response.Election;
import dev.trustproject.vocdoni.model.response.Transaction;
import dev.trustproject.vocdoni.model.response.VochainInfo;
import dev.trustproject.vocdoni.model.shared.FaucetPackage;
import dev.trustproject.vocdoni.model.shared.account.AccountMetadata;
import dev.trustproject.vocdoni.model.shared.election.ElectionMetadata;
import dev.trustproject.vocdoni.model.shared.election.ElectionStatus;
import dvote.types.v1.Vochain;
import io.vocdoni.api.AccountsApi;
import io.vocdoni.api.CensusesApi;
import io.vocdoni.api.ElectionsApi;
import io.vocdoni.invoker.ApiClient;
import io.vocdoni.invoker.ApiException;
import io.vocdoni.invoker.Configuration;
import io.vocdoni.model.*;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.*;

public class VocdoniClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final VocdoniProperties config;

    private final TransactionSigner transactionSigner;

    private final AccountsApi accountsApi;
    private final ElectionsApi electionsApi;
    private final CensusesApi censusesApi;

    public VocdoniClient(VocdoniProperties config, TransactionSigner transactionSigner) {
        this.config = config;
        this.transactionSigner = transactionSigner;

        final ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(config.apiHost() + VERSION);

        this.accountsApi = new AccountsApi(defaultClient);
        this.electionsApi = new ElectionsApi(defaultClient);
        this.censusesApi = new CensusesApi(defaultClient);
    }

    protected HttpHeaders headers() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    protected HttpHeaders headersPOST() {
        final HttpHeaders headers = headers();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public String signTransaction(String message, Vochain.Tx tx, String walletAddress) {
        return this.signTransaction(message, tx.toByteArray(), walletAddress);
    }

    public String signTransaction(String message, byte[] tx, String walletAddress) {
        return transactionSigner.signTransaction(message, tx, walletAddress);
    }

    public void waitForTransaction(String txHash) {
        this.waitForTransaction(txHash, ATTEMPTS, RETRY_TIME);
    }

    public void waitForTransaction(String txHash, int attempts, int retryTime) {
        int attemptsNumber = attempts;
        while (attemptsNumber > 0) {
            try {
                ResponseEntity<Transaction> response = restTemplate.exchange(
                        config.apiHost() + TRANSACTION_BY_HASH + "/" + txHash,
                        HttpMethod.GET,
                        new HttpEntity<>(headers()),
                        Transaction.class);

                if (response.getStatusCode() == HttpStatus.OK) {
                    break;
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Request error: " + e.getMessage());
            }

            attemptsNumber--;

            if (attemptsNumber == 0) {
                throw new RuntimeException("Time out waiting for transaction: " + txHash);
            }

            try {
                Thread.sleep(retryTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String calculateCID(String payload) throws ApiException {
        final ChainTransactionsPostRequest request = new ChainTransactionsPostRequest().payload(payload);
        return this.electionsApi.filesCidPost(request).getCid();
    }

    public VochainInfo fetchVochainInfo() {
        HttpEntity<String> httpEntity = new HttpEntity<>(headers());

        ResponseEntity<VochainInfo> response =
                restTemplate.exchange(config.apiHost() + CHAIN_INFO, HttpMethod.GET, httpEntity, VochainInfo.class);

        return response.getBody();
    }

    public FaucetPackageResponse fetchFaucetPackage(String walletAddress) throws JsonProcessingException {
        final ResponseEntity<VocdoniInternalFaucetPackage> response = restTemplate.exchange(
                config.faucetHost() + FAUCET_OPEN_CLAIM + walletAddress,
                HttpMethod.GET,
                new HttpEntity<>(headers()),
                VocdoniInternalFaucetPackage.class);

        final VocdoniInternalFaucetPackage faucetPackageInfo = response.getBody();
        final String faucetPackage = new String(Base64.getDecoder().decode(faucetPackageInfo.faucetPackage()));
        final JsonNode jsonNode = objectMapper.readTree(faucetPackage);

        return new FaucetPackageResponse(
                faucetPackageInfo.amount(),
                new FaucetPackage(
                        jsonNode.get("faucetPayload").asText(),
                        jsonNode.get("signature").asText()));
    }

    public TransactionResponse collectTokens(String walletAddress) throws JsonProcessingException, ApiException {
        Account accountInfo = fetchAccountInfo(walletAddress);
        FaucetPackage faucetPackage = fetchFaucetPackage(walletAddress).faucetPackage();

        byte[] payloadBytes = Base64.getDecoder().decode(faucetPackage.payload().getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes =
                Base64.getDecoder().decode(faucetPackage.signature().getBytes(StandardCharsets.UTF_8));

        Vochain.FaucetPackage vochainFaucetPackage = Vochain.FaucetPackage.newBuilder()
                .setPayload(ByteString.copyFrom(payloadBytes))
                .setSignature(ByteString.copyFrom(signatureBytes))
                .build();

        Vochain.CollectFaucetTx collectFaucetTx = Vochain.CollectFaucetTx.newBuilder()
                .setNonce(accountInfo.nonce())
                .setFaucetPackage(vochainFaucetPackage)
                .build();

        Vochain.Tx tx =
                Vochain.Tx.newBuilder().setCollectFaucet(collectFaucetTx).build();

        String signedTx = signTransaction(TxMessage.COLLECT_FAUCET.getMessage(), tx, walletAddress);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        HttpEntity<String> httpEntity = new HttpEntity<>(payloadJson, headersPOST());

        ResponseEntity<VocdoniInternalTransaction> response = restTemplate.exchange(
                config.apiHost() + CHAIN_TRANSACTION, HttpMethod.POST, httpEntity, VocdoniInternalTransaction.class);

        VocdoniInternalTransaction chainSubmitTxResponse = response.getBody();

        return new TransactionResponse(chainSubmitTxResponse.hash());
    }

    public AccountResponse createAccount(
            String walletAddress, FaucetPackage faucetPackage, AccountMetadata accountMetadata)
            throws JsonProcessingException, ApiException {
        String accountMetadataJson = objectMapper.writeValueAsString(accountMetadata);
        String metadata = Base64.getEncoder().encodeToString(accountMetadataJson.getBytes(StandardCharsets.UTF_8));

        String cid = calculateCID(metadata);

        Vochain.FaucetPackage faucetPackageProto = Vochain.FaucetPackage.newBuilder()
                .setPayload(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.payload())))
                .setSignature(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.signature())))
                .build();

        Vochain.SetAccountTx setAccountTx = Vochain.SetAccountTx.newBuilder()
                .setTxtype(Vochain.TxType.CREATE_ACCOUNT)
                .setNonce(1)
                .setInfoURI(cid)
                .setAccount(ByteString.fromHex(strip0x(walletAddress)))
                .setFaucetPackage(faucetPackageProto)
                .addAllDelegates(new ArrayList<>())
                .build();

        Vochain.Tx tx = Vochain.Tx.newBuilder().setSetAccount(setAccountTx).build();

        String signedTx = signTransaction(TxMessage.CREATE_ACCOUNT.getMessage(), tx, walletAddress);

        final ApiAccountSet response = this.accountsApi.accountsPost(
                new AccountsPostRequest().txPayload(signedTx).metadata(metadata));

        return new AccountResponse(response.getMetadataURL(), response.getTxHash());
    }

    public Account fetchAccountInfo(String walletAddress) throws ApiException {
        final ApiAccount response = this.accountsApi.accountsAddressGet(strip0x(walletAddress));
        return new Account(
                response.getAddress(),
                response.getBalance(),
                response.getElectionIndex(),
                response.getInfoURL(),
                response.getMetadata(),
                response.getNonce(),
                response.getSik());
    }

    public CensusResponse createCensus(String token) throws JsonProcessingException {
        HttpHeaders headers = headers();
        headers.setBearerAuth(token);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response =
                restTemplate.exchange(config.apiHost() + CENSUS_WEIGHTED, HttpMethod.POST, httpEntity, String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        return new CensusResponse(jsonNode.get("censusID").asText());
    }

    public void addParticipantsToCensus(String censusId, String token, List<CensusParticipant> participants) {
        final HttpHeaders headers = headersPOST();
        headers.setBearerAuth(token);

        Map<String, List<CensusParticipant>> requestBody = new HashMap<>();
        requestBody.put("participants", participants);
        HttpEntity<Map<String, List<CensusParticipant>>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                config.apiHost() + CENSUS + "/" + censusId + CENSUS_PARTICIPANTS,
                HttpMethod.POST,
                httpEntity,
                Void.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Request failed with status code: " + response.getStatusCode());
        }
    }

    public Census publishCensus(String censusId, String token) {
        final HttpHeaders headers = headers();
        headers.setBearerAuth(token);

        final ResponseEntity<VocdoniInternalPublishedCensus> response = restTemplate.exchange(
                config.apiHost() + CENSUS + "/" + censusId + CENSUS_PUBLISH,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                VocdoniInternalPublishedCensus.class);

        final VocdoniInternalPublishedCensus responseBody = response.getBody();
        return new Census(responseBody.censusID(), responseBody.uri());
    }

    private CensusProof fetchCensusProof(String voterAddress, String censusId, String censusToken) {
        final HttpHeaders headers = headers();
        headers.setBearerAuth(censusToken);

        final ResponseEntity<VocdoniInternalCensusProof> response = restTemplate.exchange(
                config.apiHost() + CENSUS + "/" + censusId + CENSUS_PROOF + "/" + voterAddress,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                VocdoniInternalCensusProof.class);

        final VocdoniInternalCensusProof responseBody = response.getBody();
        return new CensusProof(responseBody.censusProof(), responseBody.weight(), responseBody.value());
    }

    public Election fetchElectionInfo(String electionId) {
        HttpEntity<String> httpEntity = new HttpEntity<>(headers());

        ResponseEntity<Election> response = restTemplate.exchange(
                config.apiHost() + ELECTION + "/" + electionId, HttpMethod.GET, httpEntity, Election.class);

        return response.getBody();
    }

    public Election createElection(
            String walletAddress,
            ElectionMetadata metadata,
            Instant startDate,
            Instant endDate,
            String censusId,
            String censusURI,
            int censusSize)
            throws JsonProcessingException, ApiException {
        final Vochain.EnvelopeType envelopeType = Vochain.EnvelopeType.newBuilder()
                .setSerial(false)
                .setAnonymous(false)
                .setEncryptedVotes(false)
                .setUniqueValues(false)
                .setCostFromWeight(false)
                .build();

        final Vochain.ProcessMode mode = Vochain.ProcessMode.newBuilder()
                .setAutoStart(true)
                .setInterruptible(true)
                .setDynamicCensus(false)
                .setEncryptedMetaData(false)
                .setPreRegister(false)
                .build();

        final Vochain.ProcessVoteOptions voteOptions = Vochain.ProcessVoteOptions.newBuilder()
                .setMaxCount(metadata.questions().size())
                .setMaxValue(metadata.questions().stream()
                        .mapToInt(question -> question.choices().size())
                        .max()
                        .getAsInt())
                .setMaxVoteOverwrites(1)
                .setCostExponent(1)
                .build();

        return createElection(
                walletAddress,
                metadata,
                startDate,
                endDate,
                censusId,
                censusURI,
                censusSize,
                envelopeType,
                mode,
                voteOptions);
    }

    public Election createElection(
            String walletAddress,
            ElectionMetadata metadata,
            Instant startDate,
            Instant endDate,
            String censusId,
            String censusURI,
            int censusSize,
            Vochain.EnvelopeType envelopeType,
            Vochain.ProcessMode mode,
            Vochain.ProcessVoteOptions voteOptions)
            throws JsonProcessingException, ApiException {
        String encodedMetadata = Base64.getEncoder()
                .encodeToString(objectMapper.writeValueAsString(metadata).getBytes(StandardCharsets.UTF_8));

        String cid = calculateCID(encodedMetadata);

        VochainInfo vochainInfo = fetchVochainInfo();
        Instant blockTimestamp = vochainInfo.blockTimestamp();
        int height = vochainInfo.height();
        int[] blockTime = vochainInfo.blockTime();
        int startBlock = estimateBlockAtDateTime(startDate, blockTimestamp, height, blockTime);
        int endBlock = estimateBlockAtDateTime(endDate, blockTimestamp, height, blockTime);

        final Account accountInfo = this.fetchAccountInfo(walletAddress);

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
                .setCensusOrigin(Vochain.CensusOrigin.OFF_CHAIN_TREE_WEIGHTED)
                .setMetadata(cid)
                .setMaxCensusSize(censusSize)
                .build();

        Vochain.NewProcessTx newProcessTx = Vochain.NewProcessTx.newBuilder()
                .setTxtype(Vochain.TxType.NEW_PROCESS)
                .setNonce(accountInfo.nonce())
                .setProcess(process)
                .build();

        Vochain.Tx tx = Vochain.Tx.newBuilder().setNewProcess(newProcessTx).build();

        String signedTx = signTransaction(TxMessage.NEW_PROCESS.getMessage(), tx, walletAddress);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("txPayload", signedTx);
        payloadMap.put("metadata", encodedMetadata);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        ResponseEntity<ApiElectionCreate> response = restTemplate.exchange(
                config.apiHost() + ELECTION,
                HttpMethod.POST,
                new HttpEntity<>(payloadJson, headersPOST()),
                ApiElectionCreate.class);

        ApiElectionCreate newProcess = response.getBody();

        waitForTransaction(newProcess.getTxHash());

        return fetchElectionInfo(newProcess.getElectionID());
    }

    public void changeElectionStatus(String walletAddress, String electionId, ElectionStatus status)
            throws JsonProcessingException, ApiException {
        Account accountInfo = fetchAccountInfo(walletAddress);

        Vochain.SetProcessTx setProcessTx = Vochain.SetProcessTx.newBuilder()
                .setTxtype(Vochain.TxType.SET_PROCESS_STATUS)
                .setNonce(accountInfo.nonce())
                .setProcessId(ByteString.fromHex(strip0x(electionId)))
                .setStatus(Vochain.ProcessStatus.valueOf(status.toString()))
                .build();

        Vochain.Tx tx = Vochain.Tx.newBuilder().setSetProcess(setProcessTx).build();

        String signedTx = signTransaction(TxMessage.SET_PROCESS.getMessage(), tx, walletAddress);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson = objectMapper.writeValueAsString(payloadMap);

        restTemplate.exchange(
                config.apiHost() + CHAIN_TRANSACTION,
                HttpMethod.POST,
                new HttpEntity<>(payloadJson, headersPOST()),
                Void.class);
    }

    public List<ElectionResponse> fetchElections(String walletAddress, int page) {
        HttpEntity<String> httpEntity = new HttpEntity<>(headers());

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                config.apiHost() + ACCOUNT + "/" + walletAddress + ELECTION + PAGE + "/" + page,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {});

        return response.getBody().get("elections") != null
                ? (List<ElectionResponse>) response.getBody().get("elections")
                : null;
    }

    public VoteResponse vote(String electionId, String walletAddress, String censusToken, List<Integer> votes)
            throws JsonProcessingException, ApiException {
        final Election election = fetchElectionInfo(electionId);
        final CensusProof censusProof =
                fetchCensusProof(walletAddress, election.census().censusRoot(), censusToken);

        final Vochain.ProofArbo aProof = Vochain.ProofArbo.newBuilder()
                .setSiblings(ByteString.fromHex(censusProof.proof()))
                .setType(Vochain.ProofArbo.Type.BLAKE2B)
                .setAvailableWeight(ByteString.fromHex(censusProof.value()))
                .setKeyType(Vochain.ProofArbo.KeyType.ADDRESS)
                .build();

        final Vochain.Proof proof = Vochain.Proof.newBuilder().setArbo(aProof).build();

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

        Vochain.VoteEnvelope voteEnvelope = Vochain.VoteEnvelope.newBuilder()
                .setProof(proof)
                .setProcessId(ByteString.fromHex(strip0x(electionId)))
                .setNonce(ByteString.copyFrom(voteEnvelopeNonce))
                .setVotePackage(ByteString.copyFrom(votePackage))
                .addAllEncryptionKeyIndexes(Collections.emptyList())
                .setNullifier(ByteString.copyFrom(new byte[0]))
                .build();

        Vochain.Tx tx = Vochain.Tx.newBuilder().setVote(voteEnvelope).build();

        String signedTx = signTransaction(
                TxMessage.VOTE.getMessage().replace("{processId}", strip0x(election.electionId())), tx, walletAddress);

        Map<String, String> payload = new HashMap<>();
        payload.put("txPayload", signedTx);
        String jsonPayload = objectMapper.writeValueAsString(payload);

        final HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headersPOST());
        final ResponseEntity<VocdoniInternalVote> response =
                restTemplate.exchange(config.apiHost() + VOTE, HttpMethod.POST, entity, VocdoniInternalVote.class);

        final VocdoniInternalVote vote = response.getBody();
        return new VoteResponse(vote.getVoteID(), vote.getTxHash());
    }
}
