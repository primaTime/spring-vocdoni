package dev.trustproject.vocdoni.model.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VochainInfo {

    private String chainId;
    private int[] blockTime;
    private int electionCount;
    private Instant genesisTime;
    private int height;
    private boolean syncing;
    private Instant blockTimestamp;
    private int transactionCount;
    private int organizationCount;
    private int validatorCount;
    private int voteCount;
}
