package data;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NullException;

final public class MailAdress
{
    private final String mail;

    public MailAdress(String mail) throws EmailDoesNotExistsException, NullException
    {
        this.mail = mail;
        if (mail == null)
        {
            throw new NullException("Email cannot be NULL!");
        }

        if (!isValidEmailAddress(mail))
        {
            throw new EmailDoesNotExistsException("That mail adress doesn't exists!");
        }
    }

    public String getMailAdress()
    {
        return mail;
    }

    @Override
    public String toString()
    {
        return "MailAdress{" + "mail='" + mail + '\'' + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAdress mailAdress = (MailAdress) o;
        return mail.equals(mailAdress.mail);
    }

    @Override
    public int hashCode()
    {
        return mail.hashCode();
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}


