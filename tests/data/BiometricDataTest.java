package data;

import Exceptions.BiometricVerificationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BiometricDataTest {
    public BiometricData bio;
    public BiometricReader;
    public BiometricScanner;
    public BiometricSoftware;
    private static class BiometricReaderDoble implements BiometricReader{
        private BiometricData reader;

        @Override
        public BiometricData readBiometricData() {
            return reader;
        }
    }
    private static class BiometricScannerDoble implements BiometricScanner{
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
    private static class BiometricSoftwareDoble implements BiometricSoftware{
        @Override
        public void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException {
            new BiometricVerificationFailedException("Verification failed");
        }
    }
    @BeforeEach
    void init(){
        bio = new BiometricData();
    }
    @Test
    void readBiometricDataTest(){

    }
    @Test
    void BiometricScannerTest(){
        BigInteger face = new BigInteger();
        BigInteger touch = new BigInteger();
        assertEquals(face,bio.getFacialKey());
        assertEquals(touch,bio.getFingerPrint());
    }
    void BiometricSoftwareTest() throws BiometricVerificationFailedException(){
        assertThrows(BiometricVerificationFailedException.class, () -> bio.)
    }
}
