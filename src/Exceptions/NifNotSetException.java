package Exceptions;

public class NifNotSetException extends Throwable
{
    public NifNotSetException(String message)
    {
        System.out.println(message);
    }
}
