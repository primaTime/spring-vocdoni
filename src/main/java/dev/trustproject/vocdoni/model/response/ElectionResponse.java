package dev.trustproject.vocdoni.model.response;

import dev.trustproject.vocdoni.model.shared.election.ElectionStatus;
import java.time.Instant;

public record ElectionResponse(
        String electionId,
        String organizationId,
        ElectionStatus status,
        Instant startDate,
        Instant endDate,
        int voteCount,
        boolean finalResults) {}
