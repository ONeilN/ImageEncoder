package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESEncryptor implements Encryptor{

    Cipher cipher;

    public AESEncryptor() {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public byte[] encryptImage(byte[] image, SecretKey key) {

        byte[] encrypted = null;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(image);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    @Override
    public byte[] encryptImage(BufferedImage buffImage, SecretKey key) {

        byte[] image = null;
        byte[] encrypted = null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(buffImage, "png", baos);
            image = baos.toByteArray();

            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    @Override
    public BufferedImage encryptBuffImage(byte[] image, SecretKey key) {
        return null;
    }

    @Override
    public BufferedImage encryptBuffImage(BufferedImage buffImage, SecretKey key) {
        return null;
    }

    @Override
    public byte[] decryptImage(byte[] image, SecretKey key) {

        byte[] decrypted = null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }

    @Override
    public byte[] decryptImage(BufferedImage buffImage, SecretKey key) {
        return new byte[0];
    }

    @Override
    public BufferedImage decryptBuffImage(byte[] image, SecretKey key) {
        return null;
    }

    @Override
    public BufferedImage decryptBuffImage(BufferedImage buffImage, SecretKey key) {
        return null;
    }
}
