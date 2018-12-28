package data;

import Exceptions.EmailDoesNotExistsException;
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
        } catch (EmailDoesNotExistsException e) {
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

    @Test
    void testIsValidEmailAddress()
    {
        MailAdress emailCorrect1 = null;
        MailAdress emailCorrect2 = null;
        MailAdress emailCorrect3 = null;;
        MailAdress emailIncorrect1 = null;;
        MailAdress emailIncorrect2 = null;;
        MailAdress emailIncorrect3 = null;;
        try {
            emailCorrect1 = new MailAdress("me@gmail.com");
            emailCorrect2 = new MailAdress("me@1.com");
            emailCorrect3 = new MailAdress("me_1@1.com");

        } catch (EmailDoesNotExistsException e) {
            e.printStackTrace();
        } catch (NullException e) {
            e.printStackTrace();
        }

        assertTrue(emailCorrect1.isValidEmailAddress(emailCorrect1.getMailAdress()));
        assertTrue(emailCorrect2.isValidEmailAddress(emailCorrect2.getMailAdress()));
        assertTrue(emailCorrect3.isValidEmailAddress(emailCorrect3.getMailAdress()));

    }
}