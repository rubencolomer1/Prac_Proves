package data;

final public class MailAdress
{
    private final String mail;

    public MailAdress(String mail)
    {
        this.mail = mail;
    }
    public String getMailAdress()
    {
        return mail;
    }
    @Override
    public String toString()
    {
        return "MailAdress{" + "mail ='" + mail + '\'' + '}';
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailAdress mailAdress = (MailAdress) o;
        return mailAdress.equals(mail);
    }
    @Override
    public int hashCode()
    {
        return mail.hashCode();
    }
}
