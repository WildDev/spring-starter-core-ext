package fun.wilddev.spring.core.services.date;

import fun.wilddev.spring.core.exceptions.UnknownTimeUnitException;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.springframework.lang.NonNull;

import lombok.*;

@AllArgsConstructor
public abstract class AbstractDateCalculator implements DateCalculator {

    protected final Logger log;

    protected final DurationReader durationReader;

    protected final BiFunction<LocalDateTime, Integer, LocalDateTime> secondsFunc;

    protected final BiFunction<LocalDateTime, Integer, LocalDateTime> minutesFunc;

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
