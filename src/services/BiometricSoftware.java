package services;

import Exceptions.BiometricVerificationFailedException;
import data.BiometricData;

public interface BiometricSoftware {
    void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException, BiometricVerificationFailedException;
}