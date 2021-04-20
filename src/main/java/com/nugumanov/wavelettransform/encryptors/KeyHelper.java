package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyHelper {

    private static KeyHelper instance;

    private SecretKey key;

    private KeyHelper(EncryptionType encryptionType) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType.toString());
            keyGenerator.init(256);
            this.key = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static KeyHelper getInstance(EncryptionType encryptionType) {
        if (instance == null) {
            instance = new KeyHelper(encryptionType);
        }
        return instance;
    }

    public SecretKey getKey() {
        return key;
    }
}
