package Exceptions;

public class AlreadySetServiceException extends Throwable
{

    public AlreadySetServiceException(String message)
    {
        System.out.println(message);
    }
}
