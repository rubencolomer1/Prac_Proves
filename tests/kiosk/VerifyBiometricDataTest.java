package kiosk;

import Exceptions.BiometricVerificationFailedException;
import data.BiometricData;
import org.junit.jupiter.api.Test;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class VerifyBiometricDataTest
{
    private static class BiometricReaderDoble implements BiometricReader {
        private BiometricData reader;

        @Override
        public BiometricData readBiometricData() {
            return reader;
        }
    }
    private static class BiometricScannerDoble implements BiometricScanner {
        private BigInteger face;
        private BigInteger touch;

        @Override
        public BigInteger scanFace() {
            return face;
        }
        public BigInteger scanFingerprint() {
            return touch;
        }
    }
    private static class BiometricSoftwareDoble implements BiometricSoftware {
        @Override
        public void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException {
            new BiometricVerificationFailedException("Verification failed");
        }
    }

    @Test
    void setBiometricScanner() {
    }

    @Test
    void setBiometricReader() {
    }

    @Test
    void setBiometricSoftware() {
    }

    @Test
    void getBiometricFacials() {
    }

    @Test
    void getBiometricPassport() {
    }

    @Test
    void verify() {
    }
}