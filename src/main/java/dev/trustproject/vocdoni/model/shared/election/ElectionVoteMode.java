package dev.trustproject.vocdoni.model.shared.election;

public record ElectionVoteMode(
        Boolean serial, Boolean anonymous, Boolean encryptedVotes, Boolean uniqueValues, Boolean costFromWeight) {}
