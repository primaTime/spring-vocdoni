package dev.trustproject.vocdoni.model.response;

public record Transaction(
        int transactionNumber, String transactionHash, int blockHeight, int transactionIndex, String transactionType) {}
