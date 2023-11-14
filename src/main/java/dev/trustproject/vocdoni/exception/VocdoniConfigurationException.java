package dev.trustproject.vocdoni.exception;

public class VocdoniConfigurationException extends RuntimeException {

    public VocdoniConfigurationException() {
        super("Invalid vocdoni configuration");
    }
}
