package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.SecretKey;
import java.awt.image.BufferedImage;

public interface Encryptor {

    byte[] encryptImage(byte[] image, SecretKey key);

    byte[] encryptImage(BufferedImage image, SecretKey key);

    BufferedImage encryptBuffImage(byte[] image, SecretKey key);

    BufferedImage encryptBuffImage(BufferedImage image, SecretKey key);

    byte[] decryptImage(byte[] image, SecretKey key);

    byte[] decryptImage(BufferedImage image, SecretKey key);

    BufferedImage decryptBuffImage(byte[] image, SecretKey key);

    BufferedImage decryptBuffImage(BufferedImage image, SecretKey key);
}
