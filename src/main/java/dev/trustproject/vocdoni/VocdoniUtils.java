package dev.trustproject.vocdoni;

import com.google.protobuf.ByteString;
import dvote.types.v1.Vochain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

public class VocdoniUtils {

    public static HttpHeaders makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    public static String strip0x(String hex) {
        if (hex.startsWith("0x")) {
            return hex.substring(2);
        }
        return hex;
    }

    public static int estimateBlockAtDateTime(Instant dateTime, Instant blockTimestamp, int height, int[] blockTime) {
        Duration dateDiff = Duration.between(dateTime, blockTimestamp).abs();

        double averageBlockTime = 12000;
        double weightA, weightB;

        if (dateDiff.toMillis() >= 1000 * 60 * 60 * 24) {
            for (double blockTimeValue : blockTime) {
                if (blockTimeValue > 0) {
                    averageBlockTime = blockTimeValue;
                    break;
                }
            }
        } else {
            for (int i = 0; i < blockTime.length - 1; i++) {
                long lowerBound = 1000L * 60 * (i == 0 ? 1 : (int) Math.pow(10, i));
                long upperBound = 1000L * 60 * (i == 0 ? 10 : (int) Math.pow(10, i + 1));
                if (i == blockTime.length - 2) {
                    upperBound *= 6;
                }

                if (dateDiff.toMillis() >= lowerBound && dateDiff.toMillis() < upperBound) {
                    if (blockTime[i + 1] > 0 && blockTime[i] > 0) {
                        weightB = (dateDiff.toMillis() - lowerBound) / (double) (upperBound - lowerBound);
                        weightA = 1 - weightB;

                        averageBlockTime = weightA * blockTime[i] + weightB * blockTime[i + 1];
                    } else {
                        for (int j = i; j >= 0; j--) {
                            if (blockTime[j] > 0) {
                                averageBlockTime = blockTime[j];
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }

        double estimatedBlockDiff = dateDiff.toMillis() / averageBlockTime;
        int estimatedBlock = dateTime.isBefore(blockTimestamp)
                ? height - (int) Math.ceil(estimatedBlockDiff)
                : height + (int) Math.floor(estimatedBlockDiff);

        return Math.max(estimatedBlock, 0);
    }

    public static String signTransaction(String chainId, byte[] tx, ECKeyPair ecKeyPair) {
        //Digest Vocdoni transaction
        String prefix = "Vocdoni signed transaction:\n" + chainId + "\n";
        byte[] digestedPayload = Hash.sha3(tx);
        String digestedPayloadHex = Numeric.toHexStringNoPrefix(digestedPayload);
        String combinedPayload = prefix + digestedPayloadHex;
        byte[] prefixAndDigestedPayload = combinedPayload.getBytes(StandardCharsets.UTF_8);

        //Sign the digested payload
        Sign.SignatureData signatureData = Sign.signPrefixedMessage(prefixAndDigestedPayload, ecKeyPair);

        //Create signed transaction using the original transaction and the signature
        byte[] signature = Arrays.copyOf(signatureData.getR(), 65);
        System.arraycopy(signatureData.getS(), 0, signature, 32, 32);
        signature[64] = signatureData.getV()[0];

        Vochain.SignedTx signedTx = Vochain.SignedTx.newBuilder()
                .setTx(ByteString.copyFrom(tx))
                .setSignature(ByteString.copyFrom(signature))
                .build();

        return Base64.getEncoder().encodeToString(signedTx.toByteArray());
    }

}
