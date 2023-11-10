package dev.trustproject.vocdoni;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.trustproject.vocdoni.configuration.VocdoniTestConfiguration;
import dev.trustproject.vocdoni.model.account.AccountMedia;
import dev.trustproject.vocdoni.model.account.AccountMetadata;
import dev.trustproject.vocdoni.model.account.FaucetPackage;
import dev.trustproject.vocdoni.model.census.PublishedCensusInfo;
import dev.trustproject.vocdoni.model.process.CensusParticipant;
import dev.trustproject.vocdoni.model.process.ProcessInfo;
import dev.trustproject.vocdoni.model.process.VocdoniOption;
import dev.trustproject.vocdoni.model.process.VocdoniQuestion;
import dvote.types.v1.Vochain;
import io.vocdoni.invoker.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.*;

import static dev.trustproject.vocdoni.configuration.VocdoniTestConfiguration.*;

@SpringBootTest(
        classes = {VocdoniTestConfiguration.class}
)
@ActiveProfiles("test")
public class VocdoniClientTest {

    @Autowired
    protected VocdoniClient vocdoniClient;

    @Test
    public void all() throws JsonProcessingException, ApiException {
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

        FaucetPackage faucetPackage = vocdoniClient.getFaucet(ORGANIZATION.getAddress());
        vocdoniClient.createAccount(ORGANIZATION.getAddress(), faucetPackage, accountMetadata);

        String censusToken = UUID.randomUUID().toString();
        String censusId = vocdoniClient.createCensus(censusToken);

        vocdoniClient.addParticipantsToCensus(censusId, censusToken, List.of(new CensusParticipant(
                FIRST_ACTOR.getAddress(), "1000"), new CensusParticipant(
                SECOND_ACTOR.getAddress(), "1000")));

        PublishedCensusInfo censusInfo = vocdoniClient.publishCensus(censusId, censusToken);

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

        ProcessInfo processInfo = vocdoniClient.createProcess(
                ORGANIZATION.getAddress(),
                "test process",
                "test description",
                null,
                new HashMap<>(),
                Instant.now(),
                Instant.now().plusSeconds(60),
                censusInfo.getCensusID(),
                censusInfo.getUri(),
                List.of(new VocdoniQuestion("test question 2", null, List.of(new VocdoniOption("test option 1", 0), new VocdoniOption("test option 2", 1)))),
                2,
                envelopeType,
                mode,
                voteOptions);

        String firstVoteId = vocdoniClient.vote(
                processInfo.getElectionId(),
                FIRST_ACTOR.getAddress(),
                censusId,
                List.of(1));

        String secondVoteId = vocdoniClient.vote(
                processInfo.getElectionId(),
                SECOND_ACTOR.getAddress(),
                censusId,
                List.of(1));
    }

}
