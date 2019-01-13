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
        public boolean biometricDataVerified = false;
        public BiometricData bioScan;
        public BiometricData bioRead;

        @Override
        public void verifyBiometricData(BiometricData bioScan, BiometricData bioRead) throws BiometricVerificationFailedException
        {
            if (bioScan.equals(bioRead))
            {
                biometricDataVerified = true;
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
        BiometricData BA1 = verifyBD.getBiometricFacials();
        BiometricData BA2 = new BiometricData(new byte[] {1,2}, new byte[] {3,4});

        assertEquals(BA1, BA2);
    }

    @Test
    void testGetBiometricPassport() throws BiometricReaderAlreadySetException, BiometricReaderNotSetException, NoFacialPointsException
    {
        verifyBD.setBiometricReader(bR);
        BiometricData BA1 = verifyBD.getBiometricPassport();
        BiometricData BA2 = new BiometricData(new byte[] {1,2}, new byte[] {3,4});
        assertEquals(BA1, BA2);
    }

    @Test
    void testGotNoFacialException() throws BiometricReaderAlreadySetException, BiometricScannerAlreadySetException, BiometricSoftwareAlreadySetException, BiometricReaderNotSetException, NoFacialPointsException, BiometricVerificationFailedException, GotNoPassportException, GotNoFacialException, BiometricSoftwareNotSetException
    {
        verifyBD.setBiometricReader(bR);
        verifyBD.setBiometricScanner(bS);
        verifyBD.setBiometricSoftware(bSW);
        assertThrows(GotNoFacialException.class, () -> verifyBD.verify(null, verifyBD.getBiometricPassport()));
    }

    @Test
    void testGotNoPassportException() throws BiometricReaderAlreadySetException, BiometricScannerAlreadySetException, BiometricSoftwareAlreadySetException
    {
        verifyBD.setBiometricReader(bR);
        verifyBD.setBiometricScanner(bS);
        verifyBD.setBiometricSoftware(bSW);
        assertThrows(GotNoPassportException.class, () -> verifyBD.verify(verifyBD.getBiometricFacials(), null));
    }

    @Test
    void testVerify() throws BiometricReaderAlreadySetException, BiometricScannerAlreadySetException, BiometricSoftwareAlreadySetException, BiometricScannerNotSetException, NoFacialPointsException, BiometricReaderNotSetException, BiometricVerificationFailedException, GotNoPassportException, GotNoFacialException, BiometricSoftwareNotSetException {
        verifyBD.setBiometricReader(bR);
        verifyBD.setBiometricScanner(bS);
        verifyBD.setBiometricSoftware(bSW);

        verifyBD.verify(verifyBD.getBiometricFacials(), verifyBD.getBiometricPassport());

        assertTrue(bSW.biometricDataVerified);
        assertEquals(bSW.bioScan, new BiometricData(new byte[] {1,2}, new byte[] {3,4}));
        assertEquals(bSW.bioRead, new BiometricData(new byte[] {1,2}, new byte[] {3,4}));
    }
}