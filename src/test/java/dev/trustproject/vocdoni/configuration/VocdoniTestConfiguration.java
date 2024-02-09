package dev.trustproject.vocdoni.configuration;

import dev.trustproject.vocdoni.TransactionSigner;
import dev.trustproject.vocdoni.VocdoniUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

@TestConfiguration
public class VocdoniTestConfiguration {

    public static final Credentials ORGANIZATION;
    public static final Credentials FIRST_ACTOR;
    public static final Credentials SECOND_ACTOR;

    static {
        try {
            FIRST_ACTOR = Credentials.create(Keys.createEcKeyPair());
            SECOND_ACTOR = Credentials.create(Keys.createEcKeyPair());
            ORGANIZATION = Credentials.create(Keys.createEcKeyPair());
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public TransactionSigner transactionSigner() {
        return (message, tx, walletAddress) -> {
            ECKeyPair keyPair;

            if (ORGANIZATION.getAddress().equals(walletAddress)) {
                keyPair = ORGANIZATION.getEcKeyPair();
            } else if (FIRST_ACTOR.getAddress().equals(walletAddress)) {
                keyPair = FIRST_ACTOR.getEcKeyPair();
            } else if (SECOND_ACTOR.getAddress().equals(walletAddress)) {
                keyPair = SECOND_ACTOR.getEcKeyPair();
            } else {
                throw new RuntimeException("Unknown wallet address: " + walletAddress);
            }

            return VocdoniUtils.signTransaction(keyPair, message, tx, "vocdoni/STAGE/10");
        };
    }
}
