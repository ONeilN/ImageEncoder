package com.nugumanov.wavelettransform;

import com.nugumanov.wavelettransform.encryptors.AESEncryptor;
import com.nugumanov.wavelettransform.encryptors.EncryptionType;
import com.nugumanov.wavelettransform.encryptors.Encryptor;

public class EncryptorFactory {

    public static Encryptor getEncryptor(EncryptionType type) {
        switch (type) {
            case AES: return new AESEncryptor();
            default: return null;
        }
    }
}
