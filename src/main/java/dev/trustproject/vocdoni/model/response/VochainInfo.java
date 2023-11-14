package dev.trustproject.vocdoni.model.response;

import java.time.Instant;

public record VochainInfo(
        String chainId,
        int[] blockTime,
        int electionCount,
        Instant genesisTime,
        int height,
        boolean syncing,
        Instant blockTimestamp,
        int transactionCount,
        int organizationCount,
        int validatorCount,
        int voteCount) {}
