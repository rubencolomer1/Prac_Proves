package data;

import Exceptions.NoFacialPointsException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BiometricDataTest
{
    public Random r = new Random();
    public BigInteger bigInt = new BigInteger(10, r);

    public BiometricData b1, b2;
    {
        try
        {
            b1 = new BiometricData(bigInt, bigInt);
            b2 = new BiometricData(bigInt, bigInt);

        } catch (NoFacialPointsException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void testEquals()
    {
        assertTrue(b1.equals(b2));
    }

    @Test
    void testToString()
    {
        String facialAndPrint = "BiometricData{facial='" + bigInt + "', fingerprint='" + bigInt + "'}";
        assertEquals(facialAndPrint, b1.toString());
    }

    @Test
    void testGetFacialKey()
    {
        assertEquals(bigInt, b1.getFacialKey());
    }

    @Test
    void testGetFingerPrint()
    {
        assertEquals(bigInt,b1.getFingerPrint());
    }
}