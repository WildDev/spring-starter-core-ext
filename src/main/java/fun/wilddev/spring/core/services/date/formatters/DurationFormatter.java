package fun.wilddev.spring.core.services.date.formatters;

import fun.wilddev.spring.core.services.MessageService;
import fun.wilddev.spring.core.services.date.DurationValue;
import fun.wilddev.spring.core.stickers.BeanNames;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Formatter service for {@link DurationValue} values
 */
@Service
public class DurationFormatter {

    /**
     * Dedicated instance of {@link MessageService}
     */
    private final MessageService messageService;

    /**
     * Instantiates the class by composite params set
     *
     * @param messageSource - encapsulated version of {@link MessageSource}
     */
    public DurationFormatter(@Qualifier(BeanNames.LOCAL_MESSAGE_SOURCE) MessageSource messageSource) {
        this.messageService = new MessageService(messageSource);
    }

    /**
     * Formatter method
     *
     * @param duration - target duration value
     * @return formatted duration
     */
    public String format(@NonNull DurationValue duration) {
        return messageService.getMessage("time.unit." + duration.timeUnit().name().toLowerCase(), duration.value());
    }
}
