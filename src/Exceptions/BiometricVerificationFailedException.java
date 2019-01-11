package Exceptions;

public class BiometricVerificationFailedException extends Exception{
    public BiometricVerificationFailedException(String message) {
        System.out.println(message);
    }
}