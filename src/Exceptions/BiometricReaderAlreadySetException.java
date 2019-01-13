package Exceptions;

public class BiometricReaderAlreadySetException extends Throwable
{
    public BiometricReaderAlreadySetException(String message)
    {
        System.out.println(message);
    }
}
