package dev.trustproject.vocdoni.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaucetPackageInfo {

    private String amount;
    private String faucetPackage;
}
