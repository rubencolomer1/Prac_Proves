package data;

import Exceptions.NoFacialPointsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BiometricDataTest
{
    public BiometricData b1, b2;
    public byte[] byte1, byte2;

    @BeforeEach
    void init() throws NoFacialPointsException {
        byte1 = new byte[] {1,2};
        byte2 = new byte[] {3,4};
        b1 = new BiometricData(byte1, byte2);
        b2 = new BiometricData(byte1, byte2);
    }

    @Test
    void testEquals()
    {
        assertTrue(b1.equals(b2));
    }

    @Test
    void testToString()
    {
        String facialAndPrint = "BiometricData{facial='" + byte1 + "', fingerprint='" + byte2 + "'}";
        assertEquals(facialAndPrint, b1.toString());
    }

    @Test
    void testGetFacialKey()
    {
        assertEquals(byte1, b1.getFacialKey());
    }

    @Test
    void testGetFingerPrint()
    {
        assertEquals(byte2,b1.getFingerPrint());
    }
}