package Exceptions;

public class AlreadySetNifException extends Throwable
{
    public AlreadySetNifException(String message)
    {
        System.out.println(message);
    }
}
