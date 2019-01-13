package Exceptions;

public class BiometricScannerAlreadySetException extends Throwable
{
    public BiometricScannerAlreadySetException(String message)
    {
        System.out.println(message);
    }
}
