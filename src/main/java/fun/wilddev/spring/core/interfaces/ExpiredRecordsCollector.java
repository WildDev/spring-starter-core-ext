package fun.wilddev.spring.core.interfaces;

import java.time.LocalDateTime;
import org.springframework.lang.NonNull;

/**
 * An accumulative interface for garbage collection purposes
 */
public interface ExpiredRecordsCollector {

    /**
     * Implementations should collect context data
     * that is considered no longer actual after {@code bound}
     *
     * @param bound - a value which is close to the current timestamp
     */
    void collectExpired(@NonNull LocalDateTime bound);
}
