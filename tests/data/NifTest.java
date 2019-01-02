package data;

import Exceptions.NullException;
import Exceptions.NifDoesNotExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NifTest {
    public Nif nif1, nif2, nifCorrect1, nifCorrect2, nifCorrect3;

    @BeforeEach
    void init() throws NullException, NifDoesNotExistsException
    {
        nif1 = new Nif("48057957D");
        nif2 = new Nif("48057957D");
        nifCorrect1 = new Nif("73210173T");
        nifCorrect2 = new Nif("48057957D");
        nifCorrect3 = new Nif("12345678A");
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

    @Test
    void testIsValidEmailAddress()
    {
        assertTrue(nifCorrect1.isValidNif(nifCorrect1.getNif()));
        assertTrue(nifCorrect2.isValidNif(nifCorrect2.getNif()));
        assertTrue(nifCorrect3.isValidNif(nifCorrect3.getNif()));
    }
}