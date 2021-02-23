package com.nugumanov.wavelettransform.exeptions;

public class WaveletTransformNotDefinedException extends Exception {

    public WaveletTransformNotDefinedException() {
        super("Wavelet transform not defined!", new NullPointerException());
    }
}
