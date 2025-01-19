package fun.wilddev.spring.core.services.date.converters;

import fun.wilddev.spring.core.services.date.DurationValue;
import java.util.concurrent.TimeUnit;

import org.springframework.lang.NonNull;

/**
 * Basic interface for duration converter implementations
 */
public interface DurationConverter {

    /**
     * Converter method
     *
     * @param duration - source duration
     * @param target - target {@link TimeUnit}
     * @return converted duration value
     */
    DurationValue convert(@NonNull DurationValue duration, @NonNull TimeUnit target);
}
