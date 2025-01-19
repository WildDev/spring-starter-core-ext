package fun.wilddev.spring.core.config;

import fun.wilddev.spring.core.stickers.BeanNames;

import java.nio.charset.StandardCharsets;

import lombok.val;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.context.annotation.*;

/**
 * {@link MessageSource} configuration
 */
@Configuration
public class CoreExtensionMessageConf {

    /**
     * Encapsulated version of {@link MessageSource}
     */
    @Bean(BeanNames.LOCAL_MESSAGE_SOURCE)
    public MessageSource messageSource() {

        val target = new ResourceBundleMessageSource();

        target.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        target.setBasenames("messages/core/ext/messages");

        return target;
    }
}
