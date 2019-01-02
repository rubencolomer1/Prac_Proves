package data;

import Exceptions.NullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitalSignatureTest
{
    public DigitalSignature sign1, sign2;
    byte[] array = {1,2,3,4,5,6,7,8,9};

    @BeforeEach
    void init() throws NullException {
        sign1 = new DigitalSignature(array);
        sign2 = new DigitalSignature(array);
    }

    @Test
    void TestGetDigitalSignature()
    {
        assertEquals(array,sign1.getDigitalSignature());
    }

    @Test
    void TestToString()
    {
        String stringSign1 = "DigitalSignature{digitalSignature ='[1, 2, 3, 4, 5, 6, 7, 8, 9]'}";
        assertEquals(stringSign1,sign1.toString());
    }

    @Test
    void TestEquals()
    {
        assertTrue(sign1.equals(sign2));
    }

    @Test
    void TestHashCode()
    {
        assertTrue(sign1.hashCode()==sign2.hashCode());
    }
}