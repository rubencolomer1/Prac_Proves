package Exceptions;

public class EmailDoesNotExistsException extends Throwable {
    public EmailDoesNotExistsException(String message)
    {
        System.out.println(message);
    }
}
