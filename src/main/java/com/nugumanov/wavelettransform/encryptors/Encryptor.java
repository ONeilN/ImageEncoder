package com.nugumanov.wavelettransform.encryptors;

import javax.crypto.SecretKey;
import java.awt.image.BufferedImage;

/**
 * Общий интерфейс для алгоритмов шифрования
 */
public interface Encryptor {

    /**
     * Интерфейс метода шифрования
     * @param image байт массив изображения, который надо зашифровать
     * @param key секретный ключ для шифрования
     * @return зашифрованный массив байтов
     */
    byte[] encryptImage(byte[] image, SecretKey key);

    /**
     * Интерфейс метода шифрования
     * @param image изображение, которое надо зашифровать
     * @param key секретный ключ для шифрования
     * @return зашифрованный массив байтов
     */
    byte[] encryptImage(BufferedImage image, SecretKey key);

    /**
     * Интерфейс метода дешифрования
     * @param image байт массив изображения, который надо расшифровать
     * @param key секретный ключ для дешифрования
     * @return расшифрованное изображение в виде массива байтов
     */
    byte[] decryptImage(byte[] image, SecretKey key);

    /**
     * Интерфейс метода дешифрования
     * @param image байт массив изображения, который надо расшифровать
     * @param key секретный ключ для дешифрования
     * @return расшифрованное изображение в виде объекта BufferedImage
     */
    BufferedImage decryptImageBuffImage(byte[] image, SecretKey key);
}
