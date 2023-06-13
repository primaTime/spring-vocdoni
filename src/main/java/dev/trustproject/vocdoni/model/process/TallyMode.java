package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TallyMode {

    private int maxCount;
    private int maxValue;
    private int maxVoteOverwrites;
    private int maxTotalCost;
    private int costExponent;
}
