package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;

public interface WaveletImage {

    int getIterationCount();

    double[][] getWaveletArray();

    TransformType getTransformType();

    WaveletType getWaveletType();

    BufferedImage getSourceImage();

    BufferedImage getTransformedImage();

    BufferedImage getCroppedImage();
}
