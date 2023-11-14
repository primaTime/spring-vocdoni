package dev.trustproject.vocdoni.model.shared.election;

public record ElectionMode(
        Boolean autoStart,
        Boolean interruptible,
        Boolean dynamicCensus,
        Boolean encryptedMetaData,
        Boolean preRegister) {}
