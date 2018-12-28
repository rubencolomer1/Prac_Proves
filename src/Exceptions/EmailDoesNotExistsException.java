package Exceptions;

public class EmailDoesNotExistsException extends Exception {
    public EmailDoesNotExistsException(String message)
    {
        System.out.println(message);
    }
}
