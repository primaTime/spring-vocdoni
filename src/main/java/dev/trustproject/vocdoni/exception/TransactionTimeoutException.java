package dev.trustproject.vocdoni.exception;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class TransactionTimeoutException extends RuntimeException {

    private final String txHash;

    public TransactionTimeoutException(String txHash) {
        super(String.format("Transaction timeout: %s", txHash));
        this.txHash = txHash;
    }
}
