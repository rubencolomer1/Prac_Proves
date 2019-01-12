package Exceptions;

public class BiometricReaderNotSet extends Throwable {
    public BiometricReaderNotSet(String message)
    {
        System.out.println(message);
    }
}
