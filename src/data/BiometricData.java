package data;

import Exceptions.BiometricScannerNotSet;
import Exceptions.NoFacialPointsException;
import services.BiometricScanner;
import services.BiometricReader;
import services.BiometricSoftware;

import java.math.BigInteger;

public class BiometricData
{
    //facial points
    private BigInteger facialKey;
    private BigInteger fingerPrint;
    private BiometricScanner bS;
    private BiometricReader bR;
    private BiometricSoftware bSW;
    public boolean bSSet;
    public boolean bRSet;
    public boolean bSWSet;
    public boolean facialKeySet;
    public boolean fingerPrintSet;

    public BiometricData()
    {
        this.bRSet = false;
        this.bSSet = false;
        this.bSWSet = false;
        this.facialKeySet = false;
        this.fingerPrintSet = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometricData bio = (BiometricData) o;
        return this.facialKey.equals(bio.facialKey) && this.fingerPrint.equals(bio.fingerPrint);
    }

    @Override
    public String toString() {
        return "BiometricData{" + "facial='" + this.facialKey + '\'' + ", fingerprint='" + this.fingerPrint + '\'' +'}';
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

    public void getBiometricFacials() throws BiometricScannerNotSet
    {
        if (!bSSet)
        {
            throw new BiometricScannerNotSet("Biometric Scanner not set!");
        }
        else
        {
            this.facialKey = bS.scanFace();
            this.fingerPrint = bS.scanFingerprint();
        }

    }
}
