package Exceptions;

public class BiometricScannerNotSet extends Throwable {
    public BiometricScannerNotSet(String message)
    {
        System.out.println(message);
    }
}
