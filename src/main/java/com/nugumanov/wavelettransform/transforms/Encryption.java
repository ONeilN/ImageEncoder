package com.nugumanov.wavelettransform.transforms;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;

import java.awt.image.BufferedImage;

public interface Encryption {

    byte[] encrypt(BufferedImage buffImage, EncryptionType type, WaveletType waveletType);

    BufferedImage decrypt(byte[] encrypted, EncryptionType type, WaveletType waveletType);
}
