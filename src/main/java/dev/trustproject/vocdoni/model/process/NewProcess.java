package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProcess {

    private String txHash;
    private String electionID;
    private String metadataURL;
}
