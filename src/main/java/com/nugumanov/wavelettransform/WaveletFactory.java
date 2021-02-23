package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.transforms.WaveletType;
import com.nugumanov.wavelettransform.transforms.HaarWaveletTransform;
import com.nugumanov.wavelettransform.transforms.WaveletTransform;

/**
 * Class for implementing the factory method.
 * @author Nugumanov Aizat
 */
public class WaveletFactory {

    /**
     * Method that returns an object that implements the @{@link WaveletTransform} interface depending on the type of wavelet transform.
     * @param wavelet Enumeration for wavelet types.
     * @return An object that implements the @{@link WaveletTransform} interface depending on the type of wavelet transform.
     */
    public static WaveletTransform getTransform(WaveletType wavelet) {
        switch (wavelet) {
            case HAAR : return new HaarWaveletTransform();
            default : return null;
        }
    }
}
