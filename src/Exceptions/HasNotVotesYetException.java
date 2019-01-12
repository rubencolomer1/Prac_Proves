package Exceptions;

public class HasNotVotesYetException extends Throwable
{
    public HasNotVotesYetException(String message)
    {
        System.out.println(message);
    }
}
