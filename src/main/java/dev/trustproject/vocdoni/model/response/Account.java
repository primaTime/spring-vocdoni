package dev.trustproject.vocdoni.model.response;

import io.vocdoni.model.ApiAccountMetadata;

public record Account(
        String address,
        Integer balance,
        Integer electionIndex,
        String infoURL,
        ApiAccountMetadata metadata,
        Integer nonce,
        String sik) {}
