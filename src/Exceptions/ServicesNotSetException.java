package Exceptions;

public class ServicesNotSetException extends Throwable
{
    public ServicesNotSetException(String message)
    {
        System.out.println(message);
    }
}
