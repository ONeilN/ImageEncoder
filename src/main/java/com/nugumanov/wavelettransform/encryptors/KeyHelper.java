package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Класс для работы с секретным ключом
 * Singleton
 */
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

    /**
     * Метод для установки пароля пользователя в качестве секретного ключа
     * @param userKey пароль, введенный пользователем
     * @param type тип шифрования
     */
    public void setKey(String userKey, EncryptionType type){
        byte[] tmp = null;
        MessageDigest sha = null;
        try {
            tmp = userKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tmp = sha.digest(tmp);
        tmp = Arrays.copyOf(tmp, 16); // используем первые 128 бит

        key = new SecretKeySpec(tmp, type.toString());
    }

    /**
     * Метод для установки массива байтов в качестве секретного ключа
     * @param userKey секретный ключ в виде массива байтов
     * @param type тип шифрования
     */
    public void setKey(byte[] userKey, EncryptionType type) {
        this.key = new SecretKeySpec(userKey, type.toString());
    }

    /**
     * Метод для получения секретного ключа
     * @return секретный ключ
     */
    public SecretKey getKey() {
        return key;
    }

    /**
     * Метод для получения ключа в виде массива байтов
     * @return секретный ключ в виде массива байтов
     */
    public byte[] getByteKey() {

        byte[] byteKey = key.getEncoded();
        return byteKey;
    }

    /**
     * Метод для сохранения в файл секретного ключа
     * @param file файл, в котором будет сохранен секретный ключ
     */
    public void saveKeyToFile(File file) {
        byte[] key = this.getByteKey();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(key);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для загрузки секретного ключа из файла
     * @param file файл, который содержит секретный ключ
     * @param type тип шифрования
     */
    public void getKeyFromFile(File file, EncryptionType type) {
        byte[] key = null;
        InputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }

        try {
            key = new byte[is.available()];
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            is.read(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setKey(key, type);
    }
}
