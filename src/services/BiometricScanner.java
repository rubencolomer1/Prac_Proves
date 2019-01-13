package services;

public interface BiometricScanner {
        byte[] scanFace();
        byte[] scanFingerprint();
    }