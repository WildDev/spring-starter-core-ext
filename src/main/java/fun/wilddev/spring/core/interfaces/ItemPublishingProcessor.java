package fun.wilddev.spring.core.interfaces;

import org.springframework.lang.NonNull;

/**
 * An interface for the processing and publishing purposes
 *
 * @param <T> - item type
 *
 * @see fun.wilddev.spring.core.abstractions.PublishingPoller
 */
public interface ItemPublishingProcessor<T> {

    /**
     * Process and publish a particular item
     *
     * @param item - subject item
     * @throws Exception - thrown if the processing fails
     */
    void process(@NonNull T item) throws Exception;
}
