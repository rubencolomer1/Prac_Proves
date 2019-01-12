package kiosk;

import Exceptions.*;
import data.BiometricData;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

public class VerifyBiometricData
{
    private BiometricScanner bS;
    private BiometricReader bR;
    private BiometricSoftware bSW;
    private BiometricData bDFacial;
    private BiometricData bDPassport;
    public boolean bSSet;
    public boolean bRSet;
    public boolean bSWSet;

    public VerifyBiometricData()
    {
        this.bRSet = false;
        this.bSSet = false;
        this.bSWSet = false;
    }

    public void setBiometricScanner(BiometricScanner bS)
    {
        this.bS = bS;
        this.bSSet = true;
    }

    public void setBiometricReader(BiometricReader bR)
    {
        this.bR = bR;
        this.bRSet = true;
    }

    public void setBiometricSoftware(BiometricSoftware bSW)
    {
        this.bSW = bSW;
        this.bSWSet = true;
    }

    public void getBiometricFacials() throws BiometricScannerNotSet, NoFacialPointsException
    {
        if (!bSSet)
        {
            throw new BiometricScannerNotSet("Biometric Scanner not set!");
        }
        else
        {
            bDFacial = new BiometricData(bS.scanFace(), bS.scanFingerprint());
        }
    }

    public void getBiometricPassport() throws BiometricReaderNotSet {
        if (!bRSet)
        {
            throw new BiometricReaderNotSet("Biometric Reader not set!");
        }
        else
        {
            bDPassport = bR.readBiometricData();
        }
    }
    public void verify(BiometricData bDFacial, BiometricData bDPassport) throws BiometricSoftwareNotSet, BiometricVerificationFailedException {
        if (!bSWSet)
        {
            throw new BiometricSoftwareNotSet("Biometric Software not set!");
        }
        else
        {
            bSW.verifyBiometricData(bDFacial, bDPassport);
        }
    }
}

