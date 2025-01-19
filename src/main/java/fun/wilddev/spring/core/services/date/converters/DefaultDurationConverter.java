package fun.wilddev.spring.core.services.date.converters;

import fun.wilddev.spring.core.services.date.DurationValue;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import fun.wilddev.spring.core.services.date.converters.impl.*;

/**
 * Default bean implementation for {@link DurationConverter}
 */
@Slf4j
@Service
public class DefaultDurationConverter implements DurationConverter {

    /**
     * Weaker values converter
     */
    private final WeakerDurationConverter weakerDurationConverter;

    /**
     * Stronger values converter
     */
    private final StrongerDurationConverter strongerDurationConverter;

    /**
     * Default constructor
     */
    public DefaultDurationConverter() {

        this.weakerDurationConverter = new WeakerDurationConverter();
        this.strongerDurationConverter = new StrongerDurationConverter();
    }

    @Override
    public DurationValue convert(@NonNull DurationValue duration, @NonNull TimeUnit target) {

        val timeUnit = duration.timeUnit();

        if (timeUnit == target) {

            log.warn("Time units are equal");
            return duration;
        }

        val converter = timeUnit.ordinal() > target.ordinal() ?
                weakerDurationConverter : strongerDurationConverter;

        return converter.convert(duration, target);
    }
}
