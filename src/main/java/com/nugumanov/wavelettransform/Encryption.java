package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Интерфейс для реализации алгоритма шифрования
 * изображений при помощи вейвлет-преобразования
 */
public interface Encryption {

    /**
     * Реализация метода шифрования алгоритма шифрования изображений
     * при помощи вейвлет-преобразования
     *
     * @param buffImage изображение, которое необходимо зашифровать
     * @param type тип используемого алгоритма шифования
     * @param waveletType тип используемого вейвлет-преобразования
     * @return зашифрованный массив байтов
     */
    byte[] encrypt(BufferedImage buffImage, EncryptionType type, WaveletType waveletType);

    /**
     * Реализация метода шифрования изображений
     *
     * @param image байт массив изображения, которое необходимо зашифровать
     * @param type тип используемого алгоритма шифования
     * @return зашифрованный массив байтов
     */
    byte[] encrypt(byte[] image, EncryptionType type);

    /**
     * Реализация метода расшифрования алгоритма шифрования изображений
     * при помощи вейвлет-преобразования
     * @param encrypted массив байтов, которое необходимо расшифровать
     * @param type тип используемого алгоритма шифования
     * @param waveletType тип используемого вейвлет-преобразования
     * @return расшифрованное изображение в виде объекта BufferedImage
     */
    BufferedImage decrypt(byte[] encrypted, EncryptionType type, WaveletType waveletType);

    /**
     * Метод для сохранения зашифрованного изображения в файл
     * @param encryptedImage массив зашифрованный байтов
     * @param file файл, в который необходимо сохранить зашифрованное изображение
     */
    void saveEncryptedImageToFile(byte[] encryptedImage, File file);

    /**
     *  Метод для загрузки зашифрованных изображений
     * @param file файл, который содержить зашифрованное изображение
     * @return расшифрованный массив байтов
     */
    byte[] loadEncryptedImageFromFile(File file);
}
