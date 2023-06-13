package dev.trustproject.vocdoni.model.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {

    private int transactionNumber;
    private String transactionHash;
    private int blockHeight;
    private int transactionIndex;
    private String transactionType;
}
