package dev.trustproject.vocdoni;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import dev.trustproject.vocdoni.Vochain.Process;
import dev.trustproject.vocdoni.Vochain.*;
import dev.trustproject.vocdoni.model.account.FaucetPackage;
import dev.trustproject.vocdoni.model.account.*;
import dev.trustproject.vocdoni.model.census.CensusProof;
import dev.trustproject.vocdoni.model.census.PublishedCensusInfo;
import dev.trustproject.vocdoni.model.chain.ChainSubmitTxResponse;
import dev.trustproject.vocdoni.model.chain.TransactionInfo;
import dev.trustproject.vocdoni.model.chain.VochainInfo;
import dev.trustproject.vocdoni.model.process.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static dev.trustproject.vocdoni.VocdoniConstants.*;

@Component
public class VocdoniClient {

    @Value("${vocdoni.api.url}")
    private String apiUrl;
    @Value("${vocdoni.faucet.url}")
    private String faucetUrl;
    @Value("${vocdoni.faucet.token}")
    private String faucetToken;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static HttpHeaders makeHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    private static String strip0x(String hex) {

        if (hex.startsWith("0x")) {
            return hex.substring(2);
        }
        return hex;
    }

    private static int estimateBlockAtDateTime(Instant dateTime, Instant blockTimestamp, int height, int[] blockTime) {

        Duration dateDiff = Duration.between(dateTime, blockTimestamp).abs();

        double averageBlockTime = 12000;
        double weightA, weightB;

        if (dateDiff.toMillis() >= 1000 * 60 * 60 * 24) {
            for (double blockTimeValue : blockTime) {
                if (blockTimeValue > 0) {
                    averageBlockTime = blockTimeValue;
                    break;
                }
            }
        } else {
            for (int i = 0; i < blockTime.length - 1; i++) {
                long lowerBound = 1000L * 60 * (i == 0 ? 1 : (int) Math.pow(10, i));
                long upperBound = 1000L * 60 * (i == 0 ? 10 : (int) Math.pow(10, i + 1));
                if (i == blockTime.length - 2) {
                    upperBound *= 6;
                }

                if (dateDiff.toMillis() >= lowerBound && dateDiff.toMillis() < upperBound) {
                    if (blockTime[i + 1] > 0 && blockTime[i] > 0) {
                        weightB = (dateDiff.toMillis() - lowerBound) / (double) (upperBound - lowerBound);
                        weightA = 1 - weightB;

                        averageBlockTime = weightA * blockTime[i] + weightB * blockTime[i + 1];
                    } else {
                        for (int j = i; j >= 0; j--) {
                            if (blockTime[j] > 0) {
                                averageBlockTime = blockTime[j];
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        double estimatedBlockDiff = dateDiff.toMillis() / averageBlockTime;
        int estimatedBlock = dateTime.isBefore(blockTimestamp)
            ? height - (int) Math.ceil(estimatedBlockDiff)
            : height + (int) Math.floor(estimatedBlockDiff);

        return Math.max(estimatedBlock, 0);
    }

    public static String generatePrivateKey() {

        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            return Numeric.toHexStringNoPrefix(ecKeyPair.getPrivateKey());
        }
        catch (Exception e) {
            throw new RuntimeException("Error generating private key: " + e.getMessage());
        }
    }

    public static String getWalletAddress(String privateKey) {

        Credentials credentials = Credentials.create(privateKey);
        return credentials.getAddress();
    }

    private String signTransaction(byte[] tx, String signerKey) {

        String chainId = getVochainInfo().getChainId();

        //Digest Vocdoni transaction
        String prefix = "Vocdoni signed transaction:\n" + chainId + "\n";
        byte[] digestedPayload = Hash.sha3(tx);
        String digestedPayloadHex = Numeric.toHexStringNoPrefix(digestedPayload);
        String combinedPayload = prefix + digestedPayloadHex;
        byte[] prefixAndDigestedPayload = combinedPayload.getBytes(StandardCharsets.UTF_8);

        //Sign the digested payload
        Credentials credentials = Credentials.create(signerKey);
        Sign.SignatureData signatureData = Sign.signPrefixedMessage(prefixAndDigestedPayload, credentials.getEcKeyPair());

        //Create signed transaction using the original transaction and the signature
        byte[] signature = Arrays.copyOf(signatureData.getR(), 65);
        System.arraycopy(signatureData.getS(), 0, signature, 32, 32);
        signature[64] = signatureData.getV()[0];

        SignedTx signedTx = SignedTx.newBuilder()
            .setTx(ByteString.copyFrom(tx))
            .setSignature(ByteString.copyFrom(signature))
            .build();

        return Base64.getEncoder().encodeToString(signedTx.toByteArray());
    }

    private String getCID(String payload) {

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", payload);
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonString, makeHeaders());

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            apiUrl + CID,
            HttpMethod.POST,
            httpEntity,
            new ParameterizedTypeReference<>() {});

        return response.getBody().get("cid") != null
            ? (String) response.getBody().get("cid")
            : null;
    }

    private void waitForTransaction(String txHash) {

        int attemptsNumber = ATTEMPTS;

        HttpHeaders headers = makeHeaders();

        while (attemptsNumber > 0) {

            try {
                ResponseEntity<TransactionInfo> response = restTemplate.exchange(
                    apiUrl + TRANSACTION_BY_HASH + "/" + txHash,
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

    private CensusProof getCensusProof(String voterAddress, String censusId, String censusToken) {

        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(censusToken);

        ResponseEntity<CensusProof> response = restTemplate.exchange(
            apiUrl + CENSUSES + "/" + censusId + PROOF + "/" + voterAddress,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            CensusProof.class);

        return response.getBody();
    }

    public FaucetPackage getFaucet(String walletAddress) {

        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(faucetToken);

        ResponseEntity<FaucetPackageInfo> response = restTemplate.exchange(
            faucetUrl + "/" + walletAddress,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            FaucetPackageInfo.class);

        FaucetPackageInfo faucetPackageInfo = response.getBody();
        byte[] faucetPackageBytes  = Base64.getDecoder().decode(faucetPackageInfo.getFaucetPackage());
        String jsonString = new String(faucetPackageBytes);
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String payload = jsonNode.get("faucetPayload").asText();
        String signature = jsonNode.get("signature").asText();
        return new FaucetPackage(payload, signature);
    }

    public void collectTokens(String privateKey) {

        String walletAddress = getWalletAddress(privateKey);
        AccountInfo accountInfo = getAccountInfo(walletAddress);
        FaucetPackage faucetPackage = getFaucet(walletAddress);

        byte[] payloadBytes = Base64.getDecoder().decode(faucetPackage.getPayload().getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = Base64.getDecoder().decode(faucetPackage.getSignature().getBytes(StandardCharsets.UTF_8));

        Vochain.FaucetPackage vochainFaucetPackage = Vochain.FaucetPackage.newBuilder()
            .setPayload(ByteString.copyFrom(payloadBytes))
            .setSignature(ByteString.copyFrom(signatureBytes))
            .build();

        CollectFaucetTx collectFaucetTx = CollectFaucetTx.newBuilder()
            .setNonce(Integer.parseInt(accountInfo.getNonce()))
            .setFaucetPackage(vochainFaucetPackage)
            .build();

        Tx tx = Tx.newBuilder()
            .setCollectFaucet(collectFaucetTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, privateKey);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson;
        try {
            payloadJson = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(payloadJson, headers);

        ResponseEntity<ChainSubmitTxResponse> response = restTemplate.exchange(
            apiUrl + CHAIN_TRANSACTION,
            HttpMethod.POST,
            httpEntity,
            ChainSubmitTxResponse.class);

        ChainSubmitTxResponse chainSubmitTxResponse = response.getBody();

        waitForTransaction(chainSubmitTxResponse.getHash());
    }

    public void createAccount(String privateKey, FaucetPackage faucetPackage, AccountMetadata accountMetadata) {

        String accountMetadataJson;
        try {
            accountMetadataJson = objectMapper.writeValueAsString(accountMetadata);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String metadata = Base64.getEncoder().encodeToString(accountMetadataJson.getBytes(StandardCharsets.ISO_8859_1));

        String cid = getCID(metadata);

        Vochain.FaucetPackage faucetPackageProto = Vochain.FaucetPackage.newBuilder()
            .setPayload(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.getPayload())))
            .setSignature(ByteString.copyFrom(Base64.getDecoder().decode(faucetPackage.getSignature())))
            .build();

        String walletAddress = getWalletAddress(privateKey);

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

        String txPayload = signTransaction(tx.toByteArray(), privateKey);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("txPayload", txPayload);
        payloadMap.put("metadata", metadata);
        String payloadJson;
        try {
            payloadJson = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(payloadJson, headers);

        ResponseEntity<NewAccount> response = restTemplate.exchange(
            apiUrl + ACCOUNTS,
            HttpMethod.POST,
            httpEntity,
            NewAccount.class);

        NewAccount newAccount = response.getBody();

        waitForTransaction(newAccount.getTxHash());
    }

    public AccountInfo getAccountInfo(String walletAddress) {

        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<AccountInfo> response = restTemplate.exchange(
            apiUrl + ACCOUNTS + "/" + walletAddress,
            HttpMethod.GET,
            httpEntity,
            AccountInfo.class);

        return response.getBody();
    }

    public List<Election> getElectionList(String walletAddress, int page) {

        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            apiUrl + ACCOUNTS + "/" + walletAddress + ELECTIONS + PAGE + "/" + page,
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<>() {});

        return response.getBody().get("elections") != null
            ? (List<Election>) response.getBody().get("elections")
            : null;
    }

    public String createCensus(String token) {

        HttpHeaders headers = makeHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            apiUrl + CENSUSES + WEIGHTED,
            HttpMethod.POST,
            httpEntity,
            String.class);

        String jsonString = response.getBody();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
            apiUrl + CENSUSES + "/" + censusId + PARTICIPANTS,
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
            apiUrl + CENSUSES + "/" + censusId + PUBLISH,
            HttpMethod.POST,
            httpEntity,
            PublishedCensusInfo.class);

        return response.getBody();
    }

    public VochainInfo getVochainInfo() {

        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<VochainInfo> response = restTemplate.exchange(
            apiUrl + CHAIN_INFO,
            HttpMethod.GET,
            httpEntity,
            VochainInfo.class);

        return response.getBody();
    }

    public ProcessInfo getProcessInfo(String electionId) {

        HttpEntity<String> httpEntity = new HttpEntity<>(makeHeaders());

        ResponseEntity<ProcessInfo> response = restTemplate.exchange(
            apiUrl + ELECTIONS + "/" + electionId,
            HttpMethod.GET,
            httpEntity,
            ProcessInfo.class);

        return response.getBody();
    }

    public ProcessInfo createProcess(
        String privateKey,
        String title,
        String description,
        ProcessMedia media,
        HashMap<String, Object> meta,
        Instant startDate,
        Instant endDate,
        String censusId,
        String censusURI,
        ArrayList<VocdoniQuestion> questions
    ) {

        HashMap<String, String> titleMap = new HashMap<>();
        titleMap.put("default", title);
        HashMap<String, String> descriptionMap = new HashMap<>();
        descriptionMap.put("default", description);
        ProcessMetadata metadata = new ProcessMetadata(PROTOCOL_VERSION, titleMap, descriptionMap, media, meta, questions, null);
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(metadata);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String base64EncodedMetadata = Base64.getEncoder().encodeToString(jsonString.getBytes(StandardCharsets.ISO_8859_1));
        String cid = getCID(base64EncodedMetadata);

        VochainInfo vochainInfo = getVochainInfo();
        Instant blockTimestamp = vochainInfo.getBlockTimestamp();
        int height = vochainInfo.getHeight();
        int[] blockTime = vochainInfo.getBlockTime();
        int startBlock = estimateBlockAtDateTime(startDate, blockTimestamp, height, blockTime);
        int endBlock = estimateBlockAtDateTime(endDate, blockTimestamp, height, blockTime);

        String walletAddress = getWalletAddress(privateKey);

        AccountInfo accountInfo = getAccountInfo(walletAddress);
        int nonce = Integer.parseInt(accountInfo.getNonce());

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

        Process process = Process.newBuilder()
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
            .build();

        NewProcessTx newProcessTx = NewProcessTx.newBuilder()
            .setTxtype(TxType.NEW_PROCESS)
            .setNonce(nonce)
            .setProcess(process)
            .build();

        Tx tx = Tx.newBuilder()
            .setNewProcess(newProcessTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, privateKey);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("txPayload", signedTx);
        payloadMap.put("metadata", base64EncodedMetadata);
        String payloadJson;
        try {
            payloadJson = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseEntity<NewProcess> response = restTemplate.exchange(
            apiUrl + ELECTIONS,
            HttpMethod.POST,
            new HttpEntity<>(payloadJson, headers),
            NewProcess.class);

        NewProcess newProcess = response.getBody();

        waitForTransaction(newProcess.getTxHash());

        return getProcessInfo(newProcess.getElectionID());
    }

    public void changeProcessStatus(String privateKey, String electionId, VocdoniProcessStatus status) {

        String walletAddress = getWalletAddress(privateKey);
        AccountInfo accountInfo = getAccountInfo(walletAddress);
        int accountNonce = Integer.parseInt(accountInfo.getNonce());

        SetProcessTx setProcessTx = SetProcessTx.newBuilder()
            .setTxtype(TxType.SET_PROCESS_STATUS)
            .setNonce(accountNonce)
            .setProcessId(ByteString.fromHex(strip0x(electionId)))
            .setStatus(Vochain.ProcessStatus.valueOf(status.toString()))
            .build();

        Tx tx = Tx.newBuilder()
            .setSetProcess(setProcessTx)
            .build();

        byte[] encodedTx = tx.toByteArray();
        String signedTx = signTransaction(encodedTx, privateKey);

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("payload", signedTx);
        String payloadJson;
        try {
            payloadJson = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        restTemplate.exchange(
            apiUrl + CHAIN_TRANSACTION,
            HttpMethod.POST,
            new HttpEntity<>(payloadJson, headers),
            Void.class);
    }

    public String vote(String electionId, String voterKey, String censusToken, ArrayList<Integer> votes) {

        ProcessInfo processInfo = getProcessInfo(electionId);
        String censusId = processInfo.getCensus().getCensusRoot();

        String voterAddress = getWalletAddress(voterKey);
        CensusProof censusProof = getCensusProof(voterAddress, censusId, censusToken);

        ProofArbo aProof = ProofArbo.newBuilder()
            .setSiblings(ByteString.fromHex(censusProof.getProof()))
            .setType(ProofArbo.Type.BLAKE2B)
            .setValue(ByteString.fromHex(censusProof.getValue()))
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

        HashMap<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("nonce", votePackageNonce);
        payloadMap.put("votes", votes);
        String strPayload;
        try {
            strPayload = objectMapper.writeValueAsString(payloadMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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

        String signedTx = signTransaction(tx.toByteArray(), voterKey);

        HashMap<String, String> payload = new HashMap<>();
        payload.put("txPayload", signedTx);
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = makeHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        ResponseEntity<NewVote> response = restTemplate.exchange(
            apiUrl + VOTES,
            HttpMethod.POST,
            entity,
            NewVote.class);

        NewVote newVote = response.getBody();

        waitForTransaction(newVote.getTxHash());

        return newVote.getVoteID();
    }
}
