package Exceptions;

public class HasNotVotedYetException extends Throwable
{
    public HasNotVotedYetException(String message)
    {
        System.out.println(message);
    }
}
