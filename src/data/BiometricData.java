package data;

import Exceptions.NoFacialPointsException;
import java.math.BigInteger;

public class BiometricData
{
    //facial points
    private BigInteger facialKey;
    private BigInteger fingerPrint;

    public BiometricData(BigInteger facialKey, BigInteger fingerPrint) throws NoFacialPointsException
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
        return this.facialKey.equals(bio.facialKey) && this.fingerPrint.equals(bio.fingerPrint);
    }

    @Override
    public String toString() {
        return "BiometricData{" + "facial='" + this.facialKey + '\'' + ", fingerprint='" + this.fingerPrint + '\'' +'}';
    }

    public BigInteger getFacialKey()
    {
        return facialKey;
    }

    public BigInteger getFingerPrint()
    {
        return fingerPrint;
    }
}
