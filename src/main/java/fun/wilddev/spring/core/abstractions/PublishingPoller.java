package fun.wilddev.spring.core.abstractions;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;

import fun.wilddev.spring.core.interfaces.*;

/**
 * Publishing poller abstraction
 *
 * @param <T> - context type
 */
public abstract class PublishingPoller<T> {

    /**
     * Logger reference
     */
    protected final Logger log;

    /**
     * Chunk size to slice
     */
    protected final Integer size;

    /**
     * {@link Slicer} reference
     */
    protected final Slicer<T> slicer;

    /**
     * Filter to control how items are queued
     */
    protected final Predicate<T> filter;

    /**
     * Item publishing processor
     */
    protected final ItemPublishingProcessor<T> processor;

    /**
     * Callback to be executed as the poller execution
     * is completed. Is optional and may be null.
     */
    protected final PublishingPollerCallback callback;

    /**
     * Instantiates the class by composite params set
     *
     * @param log - logger reference
     * @param size - chunk size to slice
     * @param slicer - {@link Slicer} instance
     * @param filter - filter to control how items are queued
     * @param processor - item publishing processor
     * @param callback - callback (optional)
     */
    protected PublishingPoller(Logger log, Integer size, Slicer<T> slicer,
                               Predicate<T> filter, ItemPublishingProcessor<T> processor,
                               PublishingPollerCallback callback) {

        this.log = log;
        this.size = size;
        this.slicer = slicer;
        this.filter = filter;
        this.processor = processor;
        this.callback = callback;
    }

    /**
     * Method that loads a slice of larger data set
     * and publishes it to the target queue
     */
    public void pollAndPublish() {

        List<T> items = slicer.slice(size);

        if (items.isEmpty()) {

            log.debug("No items to process, skipping ...");
            return;
        }

        int processed = 0;

        for (T item : items) {

            try {
                if (filter != null && !filter.test(item))
                    continue;

                processor.process(item);
                processed++;

            } catch (Exception ex) {
                log.error("Failed to queue an item", ex);
            }
        }

        callback.onComplete(processed, items.size());
    }
}
