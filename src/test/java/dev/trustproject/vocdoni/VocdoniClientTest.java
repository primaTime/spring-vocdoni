package dev.trustproject.vocdoni;

import static dev.trustproject.vocdoni.configuration.VocdoniTestConfiguration.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.trustproject.vocdoni.configuration.VocdoniTestConfiguration;
import dev.trustproject.vocdoni.model.payload.CensusParticipant;
import dev.trustproject.vocdoni.model.response.*;
import dev.trustproject.vocdoni.model.shared.account.AccountMedia;
import dev.trustproject.vocdoni.model.shared.account.AccountMetadata;
import dev.trustproject.vocdoni.model.shared.election.ElectionMetadata;
import dev.trustproject.vocdoni.model.shared.election.ElectionQuestion;
import dev.trustproject.vocdoni.model.shared.election.ElectionQuestionOption;
import dvote.types.v1.Vochain;
import io.vocdoni.invoker.ApiException;
import java.time.Instant;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {VocdoniTestConfiguration.class})
@ActiveProfiles("test")
public class VocdoniClientTest {

    @Autowired
    protected VocdoniClient vocdoniClient;

    @Test
    public void all() throws JsonProcessingException, ApiException, InterruptedException {
        List<String> languages = new ArrayList<>();
        languages.add("en");

        Map<String, Object> title = Map.of("default", "Fancy organization");
        Map<String, Object> description = Map.of("default", "just testing");

        AccountMetadata accountMetadata = new AccountMetadata(
                VocdoniConstants.ACCOUNT_METADATA_VERSION,
                languages,
                title,
                description,
                new HashMap<>(),
                new AccountMedia("", "", ""),
                new HashMap<>());

        FaucetPackageResponse faucetPackage = vocdoniClient.fetchFaucetPackage(ORGANIZATION.getAddress());
        vocdoniClient.createAccount(ORGANIZATION.getAddress(), faucetPackage.faucetPackage(), accountMetadata);

        Thread.sleep(15000);

        String censusToken = UUID.randomUUID().toString();
        CensusResponse censusResponse = vocdoniClient.createCensus(censusToken);

        vocdoniClient.addParticipantsToCensus(
                censusResponse.id(),
                censusToken,
                List.of(
                        new CensusParticipant(FIRST_ACTOR.getAddress(), "1000"),
                        new CensusParticipant(SECOND_ACTOR.getAddress(), "1000")));

        Census census = vocdoniClient.publishCensus(censusResponse.id(), censusToken);

        Vochain.EnvelopeType envelopeType = Vochain.EnvelopeType.newBuilder()
                .setSerial(false)
                .setAnonymous(false)
                .setEncryptedVotes(false)
                .setUniqueValues(false)
                .setCostFromWeight(false)
                .build();

        Vochain.ProcessMode mode = Vochain.ProcessMode.newBuilder()
                .setAutoStart(true)
                .setInterruptible(true)
                .setDynamicCensus(false)
                .setEncryptedMetaData(false)
                .setPreRegister(false)
                .build();

        Vochain.ProcessVoteOptions voteOptions = Vochain.ProcessVoteOptions.newBuilder()
                .setMaxCount(1)
                .setMaxValue(2)
                .setMaxVoteOverwrites(0)
                .setCostExponent(1)
                .build();

        ElectionMetadata electionMetadata = new ElectionMetadata(
                VocdoniConstants.PROTOCOL_VERSION,
                Map.of("default", "test process"),
                Map.of("default", "test description"),
                null,
                new HashMap<>(),
                List.of(new ElectionQuestion(
                        Map.of("default", "test question 2"),
                        null,
                        List.of(
                                new ElectionQuestionOption(Map.of("default", "test option 1"), 0),
                                new ElectionQuestionOption(Map.of("default", "test option 2"), 1)))),
                null);

        ElectionTransactionResponse electionTransaction = vocdoniClient.createElection(
                ORGANIZATION.getAddress(),
                electionMetadata,
                Instant.now(),
                Instant.now().plusSeconds(60),
                census.id(),
                census.uri(),
                2,
                envelopeType,
                mode,
                voteOptions);

        vocdoniClient.waitForTransaction(electionTransaction.txHash());

        Election election = vocdoniClient.fetchElectionInfo(electionTransaction.id());

        Thread.sleep(15000);

        vocdoniClient.vote(election.electionId(), FIRST_ACTOR.getAddress(), censusToken, List.of(1));
        vocdoniClient.vote(election.electionId(), SECOND_ACTOR.getAddress(), censusToken, List.of(1));
    }

    @Test
    public void electionPriceTest() throws ApiException {
        vocdoniClient.countElectionPrice(7200, false, false, 2, 1);
    }

    @Test
    public void tokensTransfer() throws JsonProcessingException, ApiException, InterruptedException {
        List<String> languages = new ArrayList<>();
        languages.add("en");

        Map<String, Object> title = Map.of("default", "Some organization");
        Map<String, Object> description = Map.of("default", "just testing");

        AccountMetadata accountMetadata = new AccountMetadata(
                VocdoniConstants.ACCOUNT_METADATA_VERSION,
                languages,
                title,
                description,
                new HashMap<>(),
                new AccountMedia("", "", ""),
                new HashMap<>());

        FaucetPackageResponse faucetPackage = vocdoniClient.fetchFaucetPackage(FIRST_ACTOR.getAddress());
        vocdoniClient.createAccount(FIRST_ACTOR.getAddress(), faucetPackage.faucetPackage(), accountMetadata);

        FaucetPackageResponse receiverFaucetPackage = vocdoniClient.fetchFaucetPackage(SECOND_ACTOR.getAddress());
        vocdoniClient.createAccount(SECOND_ACTOR.getAddress(), receiverFaucetPackage.faucetPackage(), accountMetadata);

        Thread.sleep(15000);

        TransactionResponse transactionResponse =
                vocdoniClient.transferTokens(FIRST_ACTOR.getAddress(), SECOND_ACTOR.getAddress(), 10);
        vocdoniClient.waitForTransaction(transactionResponse.txHash());
    }
}
