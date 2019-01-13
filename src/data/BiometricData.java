package data;

import Exceptions.NoFacialPointsException;
import java.math.BigInteger;
import java.util.Arrays;

public class BiometricData
{
    //facial points
    private byte[] facialKey;
    private byte[] fingerPrint;

    public BiometricData(byte[] facialKey, byte[] fingerPrint) throws NoFacialPointsException
    {
        if (facialKey == null || fingerPrint == null)
        {
            throw new NoFacialPointsException("facialKey and fingerPrint cannot be null!");
        }
        else
        {
            this.fingerPrint = fingerPrint;
            this.facialKey = facialKey;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometricData bio = (BiometricData) o;
        return Arrays.equals(bio.facialKey, this.facialKey) && Arrays.equals(bio.fingerPrint, this.fingerPrint);
    }

    @Override
    public String toString() {
        return "BiometricData{" + "facial='" + this.facialKey + '\'' + ", fingerprint='" + this.fingerPrint + '\'' +'}';
    }

    public byte[] getFacialKey()
    {
        return facialKey;
    }

    public byte[] getFingerPrint()
    {
        return fingerPrint;
    }
}
