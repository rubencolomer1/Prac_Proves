package data;

import Exceptions.NullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailAdressTest {
    private MailAdress mail1, mail2;

    {
        try {

            mail1 = new MailAdress("rubencolomer.1@gmail.com");
            mail2 = new MailAdress("rubencolomer.1@gmail.com");

        } catch (NullException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetMailAdress()
    {
        assertEquals("rubencolomer.1@gmail.com", mail1.getMailAdress());
    }

    @Test
    void testToString()
    {
        String stringMail1 = "MailAdress{mail='rubencolomer.1@gmail.com'}";
        assertEquals(stringMail1,mail1.toString());
    }

    @Test
    void testEquals()
    {
        assertTrue(mail1.equals(mail2));
    }

    @Test
    void testHashCode()
    {
        assertTrue(mail1.hashCode()==mail2.hashCode());
    }
}