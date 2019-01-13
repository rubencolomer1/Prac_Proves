package Exceptions;

public class BiometricReaderNotSetException extends Throwable {
    public BiometricReaderNotSetException(String message)
    {
        System.out.println(message);
    }
}
