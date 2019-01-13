package Exceptions;

public class BiometricScannerNotSetException extends Throwable {
    public BiometricScannerNotSetException(String message)
    {
        System.out.println(message);
    }
}
