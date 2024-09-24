package fun.wilddev.spring.core.services.date;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.regex.*;

/**
 * Spring's style durations reader
 */
@Slf4j
@Service
public class DurationReader {

    private final static int VALUE_GROUP_ID = 1;

    private final static int TIME_UNIT_GROUP_ID = 2;

    private final static Pattern PATTERN = Pattern.compile("^([0-9]+)([a-z]+)$");

    /**
     * Default constructor
     */
    public DurationReader() {

    }

    private Integer parseValue(Matcher matcher) {
        return Integer.parseInt(matcher.group(VALUE_GROUP_ID));
    }

    private TimeUnit parseTimeUnit(Matcher matcher) {

        val timeUnit = matcher.group(TIME_UNIT_GROUP_ID);

        return switch (timeUnit) {
            case "s" -> TimeUnit.SECONDS;
            case "m" -> TimeUnit.MINUTES;
            default -> throw new IllegalArgumentException("Unknown time unit '" + timeUnit + "'");
        };
    }

    private DurationValue buildDuration(Matcher matcher) {
        return new DurationValue(parseValue(matcher), parseTimeUnit(matcher));
    }

    /**
     * Assembles an object from a duration string.
     * For example, it will read "20s" string value into DurationValue(value=20, timeUnit=SECONDS)
     *
     * @param str - duration string
     * @return an assembled {@link DurationValue} instance
     */
    public DurationValue read(@NonNull String str) {

        val matcher = PATTERN.matcher(str);

        if (matcher.matches())
            return buildDuration(matcher);

        log.warn("Incorrect duration string: {}", str);
        return null;
    }
}
