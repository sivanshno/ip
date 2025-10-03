
/**
 * Exception defined to be thrown when the user enters an unrecognised command
 *
 * @author Sivansh
 * @version 1.0
 */
public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException(String message) {
        super(message);
    }
}
