package data;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NullException;
import Exceptions.NifDoesNotExistsException;
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
        } catch (NifDoesNotExistsException e) {
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

    @Test
    void testIsValidEmailAddress()
    {
        Nif nifCorrect1 = null;
        Nif nifCorrect2 = null;
        Nif nifCorrect3 = null;
        Nif emailIncorrect1 = null;
        Nif emailIncorrect2 = null;
        Nif emailIncorrect3 = null;
        try {
            nifCorrect1 = new Nif("73210173T");
            nifCorrect2 = new Nif("48057957D");
            nifCorrect3 = new Nif("12345678A");

        } catch (NifDoesNotExistsException e) {
            e.printStackTrace();
        } catch (NullException e) {
            e.printStackTrace();
        }

        assertTrue(nifCorrect1.isValidNif(nifCorrect1.getNif()));
        assertTrue(nifCorrect2.isValidNif(nifCorrect2.getNif()));
        assertTrue(nifCorrect3.isValidNif(nifCorrect3.getNif()));

    }
}