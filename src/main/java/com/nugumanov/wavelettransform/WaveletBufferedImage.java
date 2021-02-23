package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;

/**
 * A class that contains information about the transformed image.
 * @author Nugumanov Aizat, Khialeev Azat
 */
public class WaveletBufferedImage implements WaveletImage {

    private int iterationCount;
    private double[][] waveletArray;
    private TransformType transformType;
    private WaveletType waveletType;
    private BufferedImage sourceImage;
    private BufferedImage transformedImage;

    private WaveletTransformation transformation = new ImageTransformation();

    /**
     * @param bufferedImage An object of the @{@link BufferedImage} class that contains the image to be converted.
     * @param transformType Enumeration for type of transformation.
     * @param waveletType Enumeration for wavelet types.
     * @param iterationCount Iteration count in wavelet transform.
     */
    public WaveletBufferedImage(BufferedImage bufferedImage, TransformType transformType, WaveletType waveletType, int iterationCount) {

        this.iterationCount = iterationCount;
        this.transformType = transformType;
        this.waveletType = waveletType;
        this.sourceImage = bufferedImage;
        this.transformedImage = transformation.transform(bufferedImage, transformType, waveletType, iterationCount);
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public double[][] getWaveletArray() {
        return waveletArray;
    }

    public TransformType getTransformType() {
        return transformType;
    }

    public WaveletType getWaveletType() {
        return waveletType;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public BufferedImage getTransformedImage() {
        return transformedImage;
    }

    /**
     * Method that crop the transformed image.
     * @return Cropped image.
     */
    public BufferedImage getCroppedImage() {
        // Calculating cropped image width and height
        int croppedImageWidth = this.getTransformedImage().getWidth();
        int croppedImageHeight = this.getTransformedImage().getHeight();
        int divider = (int)Math.pow(2, this.getIterationCount());
        return this.getTransformedImage().getSubimage(0, 0, croppedImageWidth / divider,
                croppedImageHeight / divider);
    }
}
