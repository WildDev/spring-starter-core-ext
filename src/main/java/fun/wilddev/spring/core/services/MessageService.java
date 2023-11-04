package fun.wilddev.spring.core.services;

import java.util.Locale;

import lombok.AllArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Customized version of {@link org.springframework.context.MessageSource}
 */
@AllArgsConstructor
@Service
public class MessageService {

    private final static String DEFAULT_MESSAGE = "Unknown message code";

    private final MessageSource messageSource;

    /**
     * Delegates a call to {@link MessageSource#getMessage(String, Object[], String, Locale)}
     *
     * @param code - message code
     * @param args - args
     * @return an interpreted message
     */
    public String getMessage(@NonNull String code, @NonNull Object... args) {
        return messageSource.getMessage(code, args, DEFAULT_MESSAGE, Locale.getDefault());
    }

    /**
     * Shortener form of {@link MessageService#getMessage(String, Object...)}
     *
     * @param code - message code
     * @return an interpreted message
     */
    public String getMessage(@NonNull String code) {
        return getMessage(code, new Object[] { });
    }
}
