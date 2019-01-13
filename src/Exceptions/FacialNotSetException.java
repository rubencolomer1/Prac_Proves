package Exceptions;

public class FacialNotSetException extends Throwable {
    public FacialNotSetException(String message)
    {
        System.out.println(message);
    }
}
