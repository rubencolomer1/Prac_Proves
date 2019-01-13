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
        this.bDFacial = null;
        this.bDPassport = null;
        this.bRSet = false;
        this.bSSet = false;
        this.bSWSet = false;
    }

    public void setBiometricScanner(BiometricScanner bS) throws BiometricScannerAlreadySetException {
        if (bSSet)
        {
            throw new BiometricScannerAlreadySetException("Biometric Scanner already set!");
        }
        else
        {
            this.bS = bS;
            this.bSSet = true;
        }
    }

    public void setBiometricReader(BiometricReader bR) throws BiometricReaderAlreadySetException {
        if (bRSet)
        {
            throw new BiometricReaderAlreadySetException("Biometric Reader already set!");
        }
        else
        {
            this.bR = bR;
            this.bRSet = true;
        }
    }

    public void setBiometricSoftware(BiometricSoftware bSW) throws BiometricSoftwareAlreadySetException {
        if (bSWSet)
        {
            throw new BiometricSoftwareAlreadySetException("Biometric Software already set!");
        }
        else
        {
            this.bSW = bSW;
            this.bSWSet = true;
        }
    }

    public BiometricData getBiometricFacials() throws BiometricScannerNotSetException, NoFacialPointsException
    {
        if (!bSSet)
        {
            throw new BiometricScannerNotSetException("Biometric Scanner not set!");
        }
        else
        {
            bDFacial = new BiometricData(bS.scanFace(), bS.scanFingerprint());
            return bDFacial;
        }
    }

    public BiometricData getBiometricPassport() throws BiometricReaderNotSetException, NoFacialPointsException {
        if (!bRSet)
        {
            throw new BiometricReaderNotSetException("Biometric Reader not set!");
        }
        else
        {
            bDPassport = bR.readBiometricData();
            return bDPassport;
        }
    }
    public void verify(BiometricData bDFacial, BiometricData bDPassport) throws BiometricSoftwareNotSetException, GotNoFacialException, GotNoPassportException, BiometricVerificationFailedException {
        if (!bSWSet)
        {
            throw new BiometricSoftwareNotSetException("Biometric Software not set!");
        }

        else if (bDFacial == null)
        {
            throw new GotNoFacialException("Got no FaceKey and FingerPrint!");
        }

        else if (bDPassport == null)
        {
            throw new GotNoPassportException("Got no Biometric Data of Passport!");
        }

        else
        {
            bSW.verifyBiometricData(bDFacial, bDPassport);
        }
    }
}

