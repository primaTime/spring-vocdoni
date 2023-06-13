package dev.trustproject.vocdoni.model.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChainSubmitTxResponse {

    private String hash;
    private String response;
    private int code;
}
