package Exceptions;

public class NifCannotVoteException extends Throwable
{
    public NifCannotVoteException(String message)
    {
        System.out.println(message);
    }
}
