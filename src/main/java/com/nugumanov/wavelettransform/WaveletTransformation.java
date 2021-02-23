package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;

public interface WaveletTransformation {

    BufferedImage transform(BufferedImage bufferedImage, TransformType transformType, WaveletType waveletType, int iterations);

    BufferedImage forwardImage(BufferedImage bufferedImage, WaveletType type, int iterations);

    double[][] forwardArray(BufferedImage bufferedImage, WaveletType type, int iterations);

    BufferedImage reverseImage(BufferedImage bufferedImage, WaveletType type, int iterations);

    double[][] reverseArray(BufferedImage bufferedImage, WaveletType type, int iterations);
}
