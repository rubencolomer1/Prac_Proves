package services;

import java.math.BigInteger;

public interface BiometricScanner {
        BigInteger scanFace();
        BigInteger scanFingerprint();
    }