package dev.trustproject.vocdoni.model.process;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Election {

    private String electionId;
    private String organizationId;
    private VocdoniProcessStatus status;
    private Instant startDate;
    private Instant endDate;
    private int voteCount;
    private boolean finalResults;
}
