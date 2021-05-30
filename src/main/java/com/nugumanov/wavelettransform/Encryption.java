package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;

public interface Encryption {

    byte[] encrypt(BufferedImage buffImage, EncryptionType type, WaveletType waveletType);

    BufferedImage decrypt(byte[] encrypted, EncryptionType type, WaveletType waveletType);
}
