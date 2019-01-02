package data;

import Exceptions.NullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest
{
    public Party partit1, partit2;

    @BeforeEach
    void init() throws NullException {
        partit1 = new Party("hola");
        partit2 = new Party("hola");
    }

    @Test
    public void TestgetName()
    {
        assertEquals("hola", partit1.getName());
    }

    @Test
    public void TestEquals()
    {
        assertTrue(partit1.equals(partit2));
    }

    @Test
    public void TesttoString()
    {
        String stringPartit1 = "Party{name='hola'}";
        assertEquals(stringPartit1,partit1.toString());
    }

    @Test
    public void TestHashCode(){
        assertTrue(partit1.hashCode()==partit2.hashCode());
    }

}