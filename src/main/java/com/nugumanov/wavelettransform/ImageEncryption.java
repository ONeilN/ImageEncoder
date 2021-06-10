package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.encryptors.Encryptor;
import com.nugumanov.wavelettransform.encryptors.KeyHelper;
import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Класс который реализует алгоритм шифрования изображений при помощи вейвлет-преобразования.
 */
public class ImageEncryption implements Encryption {

    Encryptor encryptor = null;
    ImageTransformation transformation = new ImageTransformation();
    KeyHelper keyHelper = null;

    @Override
    public byte[] encrypt(BufferedImage buffImage, EncryptionType type, WaveletType waveletType) {

        byte[] encrypted = null;
        encryptor = EncryptorFactory.getEncryptor(type);
        keyHelper = KeyHelper.getInstance(type);

        BufferedImage resultBuffImage = transformation.transform(buffImage, TransformType.FORWARD, waveletType, 2);

        encrypted = encryptor.encryptImage(resultBuffImage, keyHelper.getKey());

        return encrypted;
    }

    @Override
    public byte[] encrypt(byte[] image, EncryptionType type) {

        byte[] encrypted = null;
        encryptor = EncryptorFactory.getEncryptor(type);
        keyHelper = KeyHelper.getInstance(type);

        encrypted = encryptor.encryptImage(image, keyHelper.getKey());

        return encrypted;
    }

    @Override
    public BufferedImage decrypt(byte[] encrypted, EncryptionType type, WaveletType waveletType) {

        BufferedImage bufferedImage = null;
        BufferedImage decryptedImage = null;
        byte[] decrypted = null;

        encryptor = EncryptorFactory.getEncryptor(type);
        keyHelper = KeyHelper.getInstance(type);

        bufferedImage = encryptor.decryptImageBuffImage(encrypted, keyHelper.getKey());

        decryptedImage = transformation.transform(bufferedImage, TransformType.REVERSE, waveletType, 2);

        return decryptedImage;
    }

    @Override
    public void saveEncryptedImageToFile(byte[] encryptedImage, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(encryptedImage);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] loadEncryptedImageFromFile(File file) {

        InputStream is = null;
        byte[] encryptedImage = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            encryptedImage = new byte[is.available()];
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            is.read(encryptedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encryptedImage;
    }
}