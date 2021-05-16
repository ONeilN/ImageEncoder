package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.encryptors.Encryptor;
import com.nugumanov.wavelettransform.encryptors.KeyHelper;
import com.nugumanov.wavelettransform.transforms.Encryption;
import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;

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

        return null;
    }

}
