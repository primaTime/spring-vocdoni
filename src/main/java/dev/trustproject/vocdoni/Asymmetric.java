package dev.trustproject.vocdoni;

import com.goterl.lazysodium.LazySodiumJava;
import com.goterl.lazysodium.SodiumJava;
import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.Box;
import com.goterl.lazysodium.utils.KeyPair;
import org.bouncycastle.crypto.digests.Blake2bDigest;

public class Asymmetric {

    private static final LazySodiumJava sodium = new LazySodiumJava(new SodiumJava());

    private Asymmetric() {}

    public static byte[] encryptRaw(byte[] messageBytes, String hexPublicKey) throws SodiumException {
        byte[] publicKeyBytes = fromHex(strip0x(hexPublicKey));
        return seal(messageBytes, publicKeyBytes);
    }

    private static byte[] seal(byte[] message, byte[] publicKey) throws SodiumException {
        KeyPair ephemeralKeyPair = sodium.cryptoBoxKeypair();
        byte[] nonce = nonceGenerator(ephemeralKeyPair.getPublicKey().getAsBytes(), publicKey);
        byte[] cipherText = new byte[message.length + Box.MACBYTES];

        if (!sodium.cryptoBoxEasy(
                cipherText,
                message,
                message.length,
                nonce,
                publicKey,
                ephemeralKeyPair.getSecretKey().getAsBytes())) {
            throw new RuntimeException("Encryption failed");
        }

        byte[] combined = new byte[ephemeralKeyPair.getPublicKey().getAsBytes().length + cipherText.length];
        System.arraycopy(
                ephemeralKeyPair.getPublicKey().getAsBytes(),
                0,
                combined,
                0,
                ephemeralKeyPair.getPublicKey().getAsBytes().length);
        System.arraycopy(
                cipherText, 0, combined, ephemeralKeyPair.getPublicKey().getAsBytes().length, cipherText.length);

        return combined;
    }

    private static byte[] nonceGenerator(byte[] pk1, byte[] pk2) {
        Blake2bDigest digest = new Blake2bDigest(null, Box.NONCEBYTES, null, null);
        digest.update(pk1, 0, pk1.length);
        digest.update(pk2, 0, pk2.length);
        byte[] nonce = new byte[Box.NONCEBYTES];
        digest.doFinal(nonce, 0);
        return nonce;
    }

    private static String strip0x(String hex) {
        return hex.startsWith("0x") ? hex.substring(2) : hex;
    }

    public static byte[] fromHex(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
