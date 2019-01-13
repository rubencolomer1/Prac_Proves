package Exceptions;

public class GotNoPassportException extends Throwable
{
    public GotNoPassportException(String message)
    {
        System.out.println(message);
    }
}
