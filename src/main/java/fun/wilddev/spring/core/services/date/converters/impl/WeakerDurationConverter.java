package fun.wilddev.spring.core.services.date.converters.impl;

import java.util.Comparator;

import lombok.extern.slf4j.Slf4j;

/**
 * An implementation of {@link AbstractDurationConverter} for weaker values (minutes->seconds)
 */
@Slf4j
public class WeakerDurationConverter extends AbstractDurationConverter {

    /**
     * Default constructor
     */
    public WeakerDurationConverter() {
        super(log, Comparator.reverseOrder(),
                value -> value * 1000,
                value -> value * 1000,
                value -> value * 1000,
                value -> value * 60,
                value -> value * 60,
                value -> value * 24,
                value -> null);
    }
}
