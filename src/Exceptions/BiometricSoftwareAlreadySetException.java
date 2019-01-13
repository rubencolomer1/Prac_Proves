package Exceptions;

public class BiometricSoftwareAlreadySetException extends Throwable
{
    public BiometricSoftwareAlreadySetException(String message)
    {
        System.out.println(message);
    }
}
