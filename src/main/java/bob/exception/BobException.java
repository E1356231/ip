package bob.exception;

/**
 * Represents an application-specific exception for the chatbot
 */
public class BobException extends Exception {
    public BobException(String msg) {
        super(msg);
    }
}
