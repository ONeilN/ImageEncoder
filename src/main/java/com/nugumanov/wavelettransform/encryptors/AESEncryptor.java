package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Класс который реализует интерфейс Encryptor. Реализует алгоритм шифрования AES.
 */
public class AESEncryptor implements Encryptor {

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
    public BufferedImage decryptImageBuffImage(byte[] image, SecretKey key) {

        BufferedImage bufferedImage = null;
        byte[] decrypted = null;
        ByteArrayInputStream bais = null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        bais = new ByteArrayInputStream(decrypted);

        try {
            bufferedImage = ImageIO.read(bais);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bufferedImage;
    }
}
