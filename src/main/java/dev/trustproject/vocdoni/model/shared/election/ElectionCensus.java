package dev.trustproject.vocdoni.model.shared.election;

public record ElectionCensus(
        ElectionCensusOrigin censusOrigin, String censusRoot, String postRegisterCensusRoot, String censusURL) {}
