package fun.wilddev.spring.core.services.date;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link DateCalculator} for the dates in future
 */
@Slf4j
@Service
public class FutureCalculator extends AbstractDateCalculator {

    public FutureCalculator(DurationReader durationReader) {
        super(log, durationReader, LocalDateTime::plusSeconds, LocalDateTime::plusMinutes);
    }
}
