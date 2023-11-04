package fun.wilddev.spring.core.services.date;

import java.util.concurrent.TimeUnit;

/**
 * Duration representation object
 *
 * @param value - amount of time
 * @param timeUnit - time unit
 */
public record DurationValue(Integer value, TimeUnit timeUnit) {

}
