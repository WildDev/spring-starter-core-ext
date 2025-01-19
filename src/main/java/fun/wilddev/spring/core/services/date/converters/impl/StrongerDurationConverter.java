package fun.wilddev.spring.core.services.date.converters.impl;

import java.util.Comparator;

import lombok.extern.slf4j.Slf4j;

/**
 * An implementation of {@link AbstractDurationConverter} for stronger values (seconds->minutes)
 */
@Slf4j
public class StrongerDurationConverter extends AbstractDurationConverter {

    /**
     * Default constructor
     */
    public StrongerDurationConverter() {
        super(log, Comparator.naturalOrder(),
                value -> null,
                value -> value / 1000,
                value -> value / 1000,
                value -> value / 1000,
                value -> value / 60,
                value -> value / 60,
                value -> value / 24);
    }
}
