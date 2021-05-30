package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.encryptors.Encryptor;
import com.nugumanov.wavelettransform.encryptors.KeyHelper;
import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class ImageEncryption implements Encryption {

    Encryptor encryptor = null;
    ImageTransformation transformation = new ImageTransformation();
    KeyHelper keyHelper = null;

    public byte[] encrypt(BufferedImage buffImage, EncryptionType type, WaveletType waveletType) {

        byte[] encrypted = null;
        encryptor = EncryptorFactory.getEncryptor(type);
        keyHelper = KeyHelper.getInstance(type);

        BufferedImage resultBuffImage = transformation.transform(buffImage, TransformType.FORWARD, waveletType, 2);

        encrypted = encryptor.encryptImage(resultBuffImage, keyHelper.getKey());

        return encrypted;
    }

    public BufferedImage decrypt(byte[] encrypted, EncryptionType type, WaveletType waveletType) {

        BufferedImage bufferedImage = null;
        BufferedImage decryptedImage = null;
        byte[] decrypted = null;

        encryptor = EncryptorFactory.getEncryptor(type);
        keyHelper = KeyHelper.getInstance(type);

        decrypted = encryptor.decryptImage(encrypted, keyHelper.getKey());

        ByteArrayInputStream bais = new ByteArrayInputStream(decrypted);
        try {
            bufferedImage = ImageIO.read(bais);
        } catch (Exception e) {
            e.printStackTrace();
        }

        decryptedImage = transformation.transform(bufferedImage, TransformType.REVERSE, waveletType, 2);

        return decryptedImage;
    }

}
