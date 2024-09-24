package fun.wilddev.spring.core.exceptions;

/**
 * Is expected to be thrown when no handles available
 * for a particular {@link java.util.concurrent.TimeUnit}
 */
public class UnknownTimeUnitException extends RuntimeException {

    /**
     * Instantiates the class with the only message set
     *
     * @param message - error message
     */
    public UnknownTimeUnitException(String message) {
        super(message);
    }
}
