package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteMode {

    private Boolean serial;
    private Boolean anonymous;
    private Boolean encryptedVotes;
    private Boolean uniqueValues;
    private Boolean costFromWeight;
}
