package dev.trustproject.vocdoni.model.internal;

public record VocdoniInternalEncryptionKeys(EncryptionKey[] privateKeys, EncryptionKey[] publicKeys) {
    public record EncryptionKey(Integer index, String key) {}
}
