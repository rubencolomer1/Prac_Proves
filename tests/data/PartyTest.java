package data;

import Exceptions.NullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest
{
    private Party prova1, prova2;
    {
        try
        {
            prova1 = new Party("hola");
            prova2 = new Party("hola");
        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void TestgetName()
    {
        assertEquals("hola", prova1.getName());
    }

    @org.junit.jupiter.api.Test
    public void TesttoString()
    {
        String stringProva1 = "Party{name='hola'}";
        assertEquals(stringProva1,prova1.toString());
    }

    @org.junit.jupiter.api.Test
    public void TestEquals()
    {
        assertTrue(prova1.equals(prova2));
    }

    @org.junit.jupiter.api.Test
    public void TestHashCode(){
        assertTrue(prova1.hashCode()==prova2.hashCode());
    }

}