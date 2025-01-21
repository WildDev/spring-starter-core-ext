package fun.wilddev.spring.core.services.date.calculators;

import fun.wilddev.spring.core.exceptions.UnknownTimeUnitException;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.springframework.lang.NonNull;

import fun.wilddev.spring.core.services.date.*;
import lombok.*;

/**
 * An abstraction for datetime offset calculator implementations
 */
public abstract class AbstractDateCalculator implements DateCalculator {

    /**
     * Logger reference
     */
    protected final Logger log;

    /**
     * {@link DurationReader} bean reference
     */
    protected final DurationReader durationReader;

    /**
     * Mapping function for "seconds"
     */
    protected final BiFunction<LocalDateTime, Integer, LocalDateTime> secondsFunc;

    /**
     * Mapping function for "minutes"
     */
    protected final BiFunction<LocalDateTime, Integer, LocalDateTime> minutesFunc;

    /**
     * Instantiates the class by composite params set
     *
     * @param log - logger reference
     * @param durationReader - {@link DurationReader} bean
     * @param secondsFunc - mapping function for "seconds"
     * @param minutesFunc - mapping function for "minutes"
     */
    protected AbstractDateCalculator(Logger log, DurationReader durationReader,
                                     BiFunction<LocalDateTime, Integer, LocalDateTime> secondsFunc,
                                     BiFunction<LocalDateTime, Integer, LocalDateTime> minutesFunc) {

        this.log = log;
        this.durationReader = durationReader;
        this.secondsFunc = secondsFunc;
        this.minutesFunc = minutesFunc;
    }

    @Override
    public LocalDateTime calc(@NonNull LocalDateTime basis, @NonNull String str) {

        val duration = durationReader.read(str);

        if (duration == null) {

            log.warn("null duration provided for string {}", str);
            return null;
        }

        return calc(basis, duration);
    }

    @Override
    public LocalDateTime calc(@NonNull LocalDateTime basis, @NonNull DurationValue duration) {

        val timeUnit = duration.timeUnit();

        val func = switch (timeUnit) {
            case SECONDS -> secondsFunc;
            case MINUTES -> minutesFunc;
            default -> throw new UnknownTimeUnitException("Unknown time unit '" + timeUnit + "'");
        };

        return func.apply(basis, duration.value());
    }
}
