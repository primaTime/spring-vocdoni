package dev.trustproject.vocdoni;

public interface TransactionSigner {

    String signTransaction(String message, byte[] tx, String walletAddress);

}
