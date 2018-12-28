package data;

import Exceptions.NullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {
    private Nif nif1, nif2;

    {
        try {
            nif1 = new Nif("48057957D");
            nif2 = new Nif("48057957D");
        } catch (NullException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testGetNif()
    {
        assertEquals("48057957D", nif1.getNif());
    }

    @Test
    void testEquals()
    {
        assertTrue(nif1.equals(nif2));
    }

    @Test
    void testHashCode()
    {
        assertTrue(nif1.hashCode()==nif2.hashCode());
    }

    @Test
    void testToString()
    {
        String stringNif1 = "Nif{nif='48057957D'}";
        assertEquals(stringNif1,nif1.toString());
    }
}