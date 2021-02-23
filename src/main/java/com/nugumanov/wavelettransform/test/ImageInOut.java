package com.nugumanov.wavelettransform.test;

import java.awt.image.BufferedImage;
import java.io.File;

public interface ImageInOut {

    BufferedImage inputImage(File file);

    void outputImage(BufferedImage image, File outFile, String format);
}
