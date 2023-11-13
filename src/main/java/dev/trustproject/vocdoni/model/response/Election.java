package dev.trustproject.vocdoni.model.response;

import dev.trustproject.vocdoni.model.shared.election.ElectionCensus;
import dev.trustproject.vocdoni.model.shared.election.ElectionMetadata;
import dev.trustproject.vocdoni.model.shared.election.ElectionMode;
import dev.trustproject.vocdoni.model.shared.election.ElectionStatus;
import dev.trustproject.vocdoni.model.shared.election.ElectionTallyMode;
import dev.trustproject.vocdoni.model.shared.election.ElectionVoteMode;
import java.time.Instant;
import java.util.List;

public record Election(
        String electionId,
        String organizationId,
        ElectionCensus census,
        ElectionVoteMode voteMode,
        ElectionStatus status,
        ElectionMode electionMode,
        ElectionTallyMode tallyMode,
        ElectionMetadata metadata,
        String metadataURL,
        Instant startDate,
        Instant endDate,
        String creationTime,
        int voteCount,
        boolean finalResults,
        List<List<Integer>> result) {}
