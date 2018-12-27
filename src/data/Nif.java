package data;

public class Nif
{
    private final String nif;
    public Nif(String nif) throws NullException
    {
        this.nif = nif;
        if (nif == null)
        {
            throw new NullException("NIF cannot be null!");
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
}

