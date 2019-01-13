package Exceptions;

public class BiometricSoftwareNotSetException extends Throwable
{
    public BiometricSoftwareNotSetException(String message)
    {
        System.out.println(message);
    }
}
