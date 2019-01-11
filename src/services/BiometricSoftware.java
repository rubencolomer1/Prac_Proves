package services;

import data.BiometricData;
import Exceptions.BiometricVerificationFailedException;

public interface BiometricSoftware {
    void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException;
}