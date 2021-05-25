package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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

//    private KeyHelper(String userKey) {
//        byte[] tmp = null;
//        MessageDigest sha = null;
//        try {
//            tmp = userKey.getBytes("UTF-8");
//            sha = MessageDigest.getInstance("SHA-1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        tmp = sha.digest(tmp);
//        tmp = Arrays.copyOf(tmp, 16); // use only first 128 bit
//
//        key = new SecretKeySpec(tmp, "AES");
//    }


    public static KeyHelper getInstance(EncryptionType encryptionType) {
        if (instance == null) {
            instance = new KeyHelper(encryptionType);
        }
        return instance;
    }

    public void setKey(String userKey){
        byte[] tmp = null;
        MessageDigest sha = null;
        try {
            tmp = userKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tmp = sha.digest(tmp);
        tmp = Arrays.copyOf(tmp, 16); // use only first 128 bit

        key = new SecretKeySpec(tmp, "AES");
    }

    public void setKey(SecretKey userKey) {
        this.key = userKey;
    }

    public SecretKey getKey() {
        return key;
    }
}
