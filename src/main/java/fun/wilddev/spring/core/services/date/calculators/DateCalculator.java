package fun.wilddev.spring.core.services.date.calculators;

import fun.wilddev.spring.core.services.date.DurationValue;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

/**
 * Basic interface for datetime offset calculations
 */
public interface DateCalculator {

    /**
     * Calculator function based on duration string
     *
     * @param basis - a relative to the calculations timestamp
     * @param str - Spring's style duration string (e.g. 20s)
     * @return forwarded timestamp value
     */
    LocalDateTime calc(@NonNull LocalDateTime basis, @NonNull String str);

    /**
     * Calculator function based on {@link fun.wilddev.spring.core.services.date.DurationValue}
     *
     * @param basis - a relative to the calculations timestamp
     * @param duration - {@link fun.wilddev.spring.core.services.date.DurationValue} object
     * @return forwarded timestamp value
     */
    LocalDateTime calc(@NonNull LocalDateTime basis, @NonNull DurationValue duration);
}
