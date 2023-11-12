package dev.trustproject.vocdoni.model.process;

import dev.trustproject.vocdoni.model.census.Census;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInfo {

    private String electionId;
    private String organizationId;
    private VocdoniProcessStatus status;
    private Instant startDate;
    private Instant endDate;
    private int voteCount;
    private boolean finalResults;
    private List<List<Integer>> result;
    private Census census;
    private String metadataURL;
    private String creationTime;
    private VoteMode voteMode;
    private ElectionMode electionMode;
    private TallyMode tallyMode;
    private ProcessMetadata metadata;
}
