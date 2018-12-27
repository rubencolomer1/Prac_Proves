package data;
import Exceptions.NullException;

import java.util.Arrays;

final public class DigitalSignature
{
    private final byte[] digitalSignature;

    public DigitalSignature(byte[] digitalSignature) throws NullException
    {
        this.digitalSignature = digitalSignature;
        if (digitalSignature == null)
        {
            throw new NullException("digitalSignature cannot be null!");
        }
    }
    public byte[] getDigitalSignature()
    {
        return digitalSignature;
    }
    @Override
    public String toString()
    {
        return "DigitalSignature{" + "digitalSignature ='" + Arrays.toString(digitalSignature) + '\'' + '}';
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature sign = (DigitalSignature) o;
        return Arrays.equals(digitalSignature, sign.digitalSignature);
    }
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(digitalSignature);
    }
}
