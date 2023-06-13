package dev.trustproject.vocdoni.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    private String address;
    private String nonce;
    private int balance;
    private int electionIndex;
    private String infoURI;
    private AccountMetadata metadata;
}
