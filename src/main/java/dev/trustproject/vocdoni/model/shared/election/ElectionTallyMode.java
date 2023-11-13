package dev.trustproject.vocdoni.model.shared.election;

public record ElectionTallyMode(
        int maxCount, int maxValue, int maxVoteOverwrites, int maxTotalCost, int costExponent) {}
