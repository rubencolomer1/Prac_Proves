package kiosk;

import Exceptions.*;
import data.BiometricData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

import static org.junit.jupiter.api.Assertions.*;

class VerifyBiometricDataTest
{
    public BiometricScanner bS;
    public BiometricReader bR;
    public BiometricSoftware bSW;
    public VerifyBiometricData verifyBD;

    private static class BiometricScannerDoble implements  BiometricScanner
    {
        @Override
        public byte[] scanFace()
        {
            return new byte[] {1,2};
        }

        @Override
        public byte[] scanFingerprint()
        {
            return new byte[] {3,4};
        }
    }

    private static class BiometricReaderDoble implements BiometricReader
    {
        @Override
        public BiometricData readBiometricData() throws NoFacialPointsException
        {
            return new BiometricData(new byte[] {1,2}, new byte[] {3,4});
        }
    }

    private static class BiometricSoftwareDoble implements BiometricSoftware
    {
        public boolean BiometricDataVerified = false;
        public BiometricData bioScan;
        public BiometricData bioRead;

        @Override
        public void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException
        {
            if (bioScan.equals(bioRead))
            {
                BiometricDataVerified = true;
                this.bioScan = bioScan;
                this.bioRead = bioRead;
            }
            else
            {
                throw new BiometricVerificationFailedException("Ha fallat la verificació!");
            }
        }
    }

    @BeforeEach
    void init()
    {
        bS = new BiometricScannerDoble();
        bR = new BiometricReaderDoble();
        bSW = new BiometricSoftwareDoble();
        verifyBD = new VerifyBiometricData();
    }

    @Test
    void testBiometricScannerAlreadySet() throws BiometricReaderAlreadySetException
    {
        verifyBD.setBiometricReader(bR);
        assertThrows(BiometricReaderAlreadySetException.class, () -> verifyBD.setBiometricReader(bR));
    }

    @Test
    void testBiometricReaderAlreadySet() throws BiometricScannerAlreadySetException
    {
        verifyBD.setBiometricScanner(bS);
        assertThrows(BiometricScannerAlreadySetException.class, () -> verifyBD.setBiometricScanner(bS));
    }

    @Test
    void testBiometricSoftwareAlreadySet() throws BiometricSoftwareAlreadySetException
    {
        verifyBD.setBiometricSoftware(bSW);
        assertThrows(BiometricSoftwareAlreadySetException.class, () -> verifyBD.setBiometricSoftware(bSW));
    }

    @Test
    void testBiometricScannerNotSet() throws BiometricScannerNotSetException, NoFacialPointsException
    {
        assertThrows(BiometricScannerNotSetException.class, () -> verifyBD.getBiometricFacials());
    }

    @Test
    void testBiometricReaderNotSet()
    {
        assertThrows(BiometricReaderNotSetException.class, () -> verifyBD.getBiometricPassport());
    }

    @Test
    void testBiometricSoftwareNotSet() throws BiometricScannerAlreadySetException, BiometricReaderAlreadySetException, BiometricScannerNotSetException, NoFacialPointsException, BiometricReaderNotSetException
    {
        verifyBD.setBiometricScanner(bS);
        verifyBD.setBiometricReader(bR);

        assertThrows(BiometricSoftwareNotSetException.class, () -> verifyBD.verify(verifyBD.getBiometricFacials(),verifyBD.getBiometricPassport()));
    }

    @Test
    void testGetBiometricFacials() throws BiometricScannerAlreadySetException, BiometricScannerNotSetException, NoFacialPointsException
    {
        verifyBD.setBiometricScanner(bS);
        BiometricData BA = verifyBD.getBiometricFacials();

        assertEquals(BA, new BiometricData(new byte[] {1,2}, new byte[] {3,4}));
    }

    @Test
    void testGetBiometricPassport()
    {

    }

    @Test
    void testVerify()
    {

    }
}