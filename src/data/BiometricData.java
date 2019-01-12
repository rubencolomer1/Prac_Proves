package data;

import Exceptions.NoFacialPointsException;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

import java.math.BigInteger;

public class BiometricData
{
    //facial points
    public BigInteger facialKey;
    public BigInteger fingerPrint;
    private BiometricScanner bS;
    private BiometricReader bR;
    private BiometricSoftware bSW;

    public BiometricData(BigInteger FacialKey,BigInteger fingerPrint) throws NoFacialPointsException
    {
        if (facialKey == null || fingerPrint == null)
        {
            throw new NoFacialPointsException("Facial point cannot be null!");
        }
        else
        {
            this.facialKey = facialKey;
            this.fingerPrint = fingerPrint;
        }
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
    }
    public void setBiometricReader(BiometricReader bR)
    {
        this.bR = bR;
    }

    public void setBiometricSoftware(BiometricSoftware bSW)
    {
        this.bSW = bSW;
    }

    public void getBiometricFacials()
    {
        this.facialKey = bS.scanFace();
        this.fingerPrint = bS.scanFingerprint();
    }

}
