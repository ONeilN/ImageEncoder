package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.exeptions.WaveletTransformNotDefinedException;
import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;
import com.nugumanov.wavelettransform.transforms.WaveletTransform;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that implements forward and reverse wavelet image conversion
 * @author Nugumanov Aizat
 */
public class ImageTransformation implements WaveletTransformation {

    private WaveletTransform waveletTransform = null;

    /**
     * A method that implements a wavelet transform depending on the type of transform and type of wavelet.
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param transformType Enumeration for type of transformation.
     * @param waveletType Enumeration for wavelet types.
     * @param iterations Iteration count in wavelet transform.
     * @return An object of the @{@link BufferedImage} class that contains the converted image.
     */
    public BufferedImage transform(BufferedImage bufferedImage, TransformType transformType, WaveletType waveletType, int iterations) {
        switch (transformType) {
            case FORWARD:
                return forwardImage(bufferedImage, waveletType, iterations);
            case REVERSE:
                return reverseImage(bufferedImage, waveletType, iterations);
            default: return null;
        }
    }

    /**
     * A method that implements a forward wavelet transform depending on the type of wavelet.
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param type Enumeration for wavelet types.
     * @param iterations Iteration count in wavelet transform.
     * @return An object of the @{@link BufferedImage} class that contains the converted image.
     */
    public BufferedImage forwardImage(BufferedImage bufferedImage, WaveletType type, int iterations) {

        if (type == null) {
            try {
                throw new WaveletTransformNotDefinedException();
            } catch (WaveletTransformNotDefinedException e) {
                e.printStackTrace();
            }
        }
        waveletTransform = WaveletFactory.getTransform(type);
        BufferedImage resultImage = bufferedImage;

        int width = bufferedImage.getWidth();    // Ширина изображения
        int height = bufferedImage.getHeight();  // Высота изображения

        Color c;            // Переменная для хранения цвета пикселя

        double[][] Red = new double[width][height];
        double[][] Green = new double[width][height];
        double[][] Blue = new double[width][height];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color(bufferedImage.getRGB(i, j));
                Red[i][j] = Scale(0, 255, -1, 1, c.getRed());
                Green[i][j] = Scale(0, 255, -1, 1, c.getGreen());
                Blue[i][j] = Scale(0, 255, -1, 1, c.getBlue());
            }
        }

        waveletTransform.FWT(Red, iterations, width, height);
        waveletTransform.FWT(Green, iterations, width, height);
        waveletTransform.FWT(Blue, iterations, width, height);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color((int) Scale(-1, 1, 0, 255, Red[i][j]),
                        (int) Scale(-1, 1, 0, 255, Green[i][j]),
                        (int) Scale(-1, 1, 0, 255, Blue[i][j]));
                resultImage.setRGB(i, j, c.getRGB());
            }
        }

        return resultImage;
    }

    /**
     * A method that implements a forward wavelet transform depending on the type of wavelet.
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param type Enumeration for wavelet types.
     * @param iterations Iteration count in wavelet transform.
     * @return Two-dimensional array containing converted RGB values.
     */
    public double[][] forwardArray(BufferedImage bufferedImage, WaveletType type, int iterations) {

        if (type == null) {
            try {
                throw new WaveletTransformNotDefinedException();
            } catch (WaveletTransformNotDefinedException e) {
                e.printStackTrace();
            }
        }
        waveletTransform = WaveletFactory.getTransform(type);
        BufferedImage resultImage = bufferedImage;

        int width = bufferedImage.getWidth();    // Ширина изображения
        int height = bufferedImage.getHeight();  // Высота изображения

        Color c;            // Переменная для хранения цвета пикселя

        double[][] Red = new double[width][height];
        double[][] Green = new double[width][height];
        double[][] Blue = new double[width][height];

        double[][] transformedArray = new double[3 * width][3 * height];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color(bufferedImage.getRGB(i, j));
                Red[i][j] = Scale(0, 255, -1, 1, c.getRed());
                Green[i][j] = Scale(0, 255, -1, 1, c.getGreen());
                Blue[i][j] = Scale(0, 255, -1, 1, c.getBlue());
            }
        }

        waveletTransform.FWT(Red, iterations, width, height);
        waveletTransform.FWT(Green, iterations, width, height);
        waveletTransform.FWT(Blue, iterations, width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                transformedArray[i][j] = Red[i][j];
                transformedArray[i + 1][j + 1] = Green[i][j];
                transformedArray[i + 2][j + 2] = Blue[i][j];
            }
        }

        return transformedArray;
    }

    /**
     * A method that implements a reverse wavelet transform depending on the type of wavelet.
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param type Enumeration for wavelet types.
     * @param iterations Iteration count in wavelet transform.
     * @return An object of the @{@link BufferedImage} class that contains the converted image.
     */
    public BufferedImage reverseImage(BufferedImage bufferedImage, WaveletType type, int iterations) {

        if (type == null) {
            try {
                throw new WaveletTransformNotDefinedException();
            } catch (WaveletTransformNotDefinedException e) {
                e.printStackTrace();
            }
        }
        waveletTransform = WaveletFactory.getTransform(type);
        BufferedImage resultImage = bufferedImage;

        int width = bufferedImage.getWidth();    // Ширина изображения
        int height = bufferedImage.getHeight();  // Высота изображения

        Color c;            // Переменная для хранения цвета пикселя

        double[][] Red = new double[width][height];
        double[][] Green = new double[width][height];
        double[][] Blue = new double[width][height];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color(bufferedImage.getRGB(i, j));
                Red[i][j] = Scale(0, 255, -1, 1, c.getRed());
                Green[i][j] = Scale(0, 255, -1, 1, c.getGreen());
                Blue[i][j] = Scale(0, 255, -1, 1, c.getBlue());
            }
        }

        waveletTransform.IWT(Red, iterations, width, height);
        waveletTransform.IWT(Green, iterations, width, height);
        waveletTransform.IWT(Blue, iterations, width, height);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color((int) Scale(-1, 1, 0, 255, Red[i][j]),
                        (int) Scale(-1, 1, 0, 255, Green[i][j]),
                        (int) Scale(-1, 1, 0, 255, Blue[i][j]));
                resultImage.setRGB(i, j, c.getRGB());
            }
        }

        return resultImage;
    }

    /**
     * A method that implements a reverse wavelet transform depending on the type of wavelet.
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param type Enumeration for wavelet types.
     * @param iterations Iteration count in wavelet transform.
     * @return Two-dimensional array containing converted RGB values.
     */
    public double[][] reverseArray(BufferedImage bufferedImage, WaveletType type, int iterations) {

        if (type == null) {
            try {
                throw new WaveletTransformNotDefinedException();
            } catch (WaveletTransformNotDefinedException e) {
                e.printStackTrace();
            }
        }
        waveletTransform = WaveletFactory.getTransform(type);
        BufferedImage resultImage = bufferedImage;

        int width = bufferedImage.getWidth();    // Ширина изображения
        int height = bufferedImage.getHeight();  // Высота изображения

        Color c;            // Переменная для хранения цвета пикселя

        double[][] Red = new double[width][height];
        double[][] Green = new double[width][height];
        double[][] Blue = new double[width][height];

        double[][] transformedArray = new double[3 * width][3 * height];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c = new Color(bufferedImage.getRGB(i, j));
                Red[i][j] = Scale(0, 255, -1, 1, c.getRed());
                Green[i][j] = Scale(0, 255, -1, 1, c.getGreen());
                Blue[i][j] = Scale(0, 255, -1, 1, c.getBlue());
            }
        }

        waveletTransform.IWT(Red, iterations, width, height);
        waveletTransform.IWT(Green, iterations, width, height);
        waveletTransform.IWT(Blue, iterations, width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                transformedArray[i][j] = Red[i][j];
                transformedArray[i + 1][j + 1] = Green[i][j];
                transformedArray[i + 2][j + 2] = Blue[i][j];
            }
        }

        return transformedArray;
    }

    /**
     * Method that normalizes the passed value.
     * @param fromMin The lower limit of the value to be normalized.
     * @param fromMax The upper limit of the value to be normalized.
     * @param toMin The lower limit of the normalized value.
     * @param toMax The upper limit of the normalized value.
     * @param x The value to be normalized.
     * @return Normalized value.
     */
    public double Scale(double fromMin, double fromMax, double toMin, double toMax, double x) {

        if (fromMax - fromMin == 0) return 0;
        double value = (toMax - toMin) * (x - fromMin) / (fromMax - fromMin) + toMin;
        if (value > toMax) {
            value = toMax;
        }
        if (value < toMin) {
            value = toMin;
        }
        return value;
    } // Scale
}
