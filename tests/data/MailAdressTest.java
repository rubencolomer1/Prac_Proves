package data;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailAdressTest
{
    public MailAdress mail1, mail2, emailCorrect1, emailCorrect2, emailCorrect3;

    @BeforeEach
    void init() throws NullException, EmailDoesNotExistsException
    {
        mail1 = new MailAdress("rubencolomer.1@gmail.com");
        mail2 = new MailAdress("rubencolomer.1@gmail.com");
        emailCorrect1 = new MailAdress("me@gmail.com");
        emailCorrect2 = new MailAdress("me@1.com");
        emailCorrect3 = new MailAdress("me_1@1.com");
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

    @Test
    void testIsValidEmailAddress()
    {
        assertTrue(emailCorrect1.isValidEmailAddress(emailCorrect1.getMailAdress()));
        assertTrue(emailCorrect2.isValidEmailAddress(emailCorrect2.getMailAdress()));
        assertTrue(emailCorrect3.isValidEmailAddress(emailCorrect3.getMailAdress()));
    }
}