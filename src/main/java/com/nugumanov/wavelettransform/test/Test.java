package com.nugumanov.wavelettransform.test;

import com.nugumanov.wavelettransform.*;
import com.nugumanov.wavelettransform.transforms.TransformType;
import com.nugumanov.wavelettransform.transforms.WaveletType;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * A class that implements forward and reverse wavelet image conversion
 * @author Nugumanov Aizat, Khialeev Azat
 */
public class Test {
    public static void main(String[] args) {

        ImageInOut imageInOut = new ImageOpenSave();

        /**
         *  Forward Test
         */
        File forwardInputFile = new File("src/main/resources/testimages/test3.png");

        BufferedImage forwardImage = imageInOut.inputImage(forwardInputFile);

        WaveletImage waveletForwardImage = new WaveletBufferedImage(forwardImage, TransformType.FORWARD, WaveletType.HAAR, 2);

        File forwardOutputFile = new File("src/main/resources/resultimages/haar.forward.test3.png");
        imageInOut.outputImage(waveletForwardImage.getTransformedImage(), forwardOutputFile, "png");

        /**
         * Reverse Test
         */
        File reverseInputFile = new File("src/main/resources/testimages/haar.forward.test3.png");

        BufferedImage reverseImage = imageInOut.inputImage(reverseInputFile);

        WaveletImage waveletReverseImage = new WaveletBufferedImage(reverseImage, TransformType.REVERSE, WaveletType.HAAR, 2);

        File reverseOutputFile = new File("src/main/resources/resultimages/haar.reverse.test3.png");
        imageInOut.outputImage(waveletReverseImage.getTransformedImage(), reverseOutputFile, "png");

        File croppedOutputFile = new File("src/main/resources/resultimages/cropped.png");
        imageInOut.outputImage(waveletForwardImage.getCroppedImage(), croppedOutputFile, "png");
    }
}
