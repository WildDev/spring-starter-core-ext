package fun.wilddev.spring.core.services.date;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * An implementation of {@link DateCalculator} for the dates in past
 */
@Slf4j
@Service
public class PastCalculator extends AbstractDateCalculator {

    /**
     * Instantiates the class by {@link DurationReader}
     *
     * @param durationReader - {@link DurationReader} bean
     */
    public PastCalculator(DurationReader durationReader) {
        super(log, durationReader, LocalDateTime::minusSeconds, LocalDateTime::minusMinutes);
    }
}
