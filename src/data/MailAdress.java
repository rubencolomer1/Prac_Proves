package data;

import Exceptions.NullException;

final public class MailAdress
{
    private final String mail;

    public MailAdress(String mail) throws NullException
    {
        this.mail = mail;
        if (mail == null)
        {
            throw new NullException("Mail adress cannot be null!");
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
}
