package dev.trustproject.vocdoni.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaucetPackage {

    private String payload;
    private String signature;
}
