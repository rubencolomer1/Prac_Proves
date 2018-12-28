package data;

import Exceptions.EmailDoesNotExistsException;
import Exceptions.NullException;
import Exceptions.NifDoesNotExistsException;

public class Nif
{
    private final String nif;
    public Nif(String nif) throws NifDoesNotExistsException, NullException
    {
        this.nif = nif;
        if (nif == null)
        {
            throw new NullException("NIF cannot be null!");
        }
        if (!isValidNif(nif))
        {
            throw new NifDoesNotExistsException("That NIF doesn't exists!");
        }
    }
    public String getNif()
    {
        return nif;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nif dni = (Nif) o;
        return nif.equals(dni.nif);
    }
    @Override
    public int hashCode()
    {
        return nif.hashCode();
    }

    @Override
    public String toString()
    {
        return "Nif{" + "nif='" + nif + '\'' + '}';
    }

    public boolean isValidNif(String nif) {
        String nifPattern = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(nifPattern);
        java.util.regex.Matcher m = p.matcher(nif);
        return m.matches();
    }
}

