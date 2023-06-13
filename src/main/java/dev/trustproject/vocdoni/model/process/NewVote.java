package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewVote {

    private String voteID;
    private String txHash;
}
