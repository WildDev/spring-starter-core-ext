package fun.wilddev.spring.core.exceptions;

/**
 * Is expected to be thrown when no handles available
 * for a particular {@link java.util.concurrent.TimeUnit}
 */
public class UnknownTimeUnitException extends RuntimeException {

    public UnknownTimeUnitException(String message) {
        super(message);
    }
}
