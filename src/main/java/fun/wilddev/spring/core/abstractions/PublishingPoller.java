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
     * Target queue where sliced items are moved
     */
    protected final Queue<T> queue;

    /**
     * Filter to control how items are queued
     */
    protected final Predicate<T> filter;

    /**
     * Preprocessing task to be triggered before each item
     * is published to the target queue. Is optional and may be null.
     */
    protected final Task<T> preprocessingTask;

    /**
     * Postprocessing task to be triggered after each item
     * is published to the target queue. Is optional and may be null.
     */
    protected final Task<T> postprocessingTask;

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
     * @param queue - target queue where sliced items are moved
     * @param filter - filter to control how items are queued
     * @param preprocessingTask - preprocessing task (optional)
     * @param postprocessingTask - postprocessing task (optional)
     * @param callback - callback (optional)
     */
    protected PublishingPoller(Logger log, Integer size, Slicer<T> slicer,
                               Queue<T> queue, Predicate<T> filter,
                               Task<T> preprocessingTask, Task<T> postprocessingTask,
                               PublishingPollerCallback callback) {

        this.log = log;
        this.size = size;
        this.slicer = slicer;
        this.queue = queue;
        this.filter = filter;
        this.preprocessingTask = preprocessingTask;
        this.postprocessingTask = postprocessingTask;
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

                if (preprocessingTask != null)
                    preprocessingTask.run(item);

                queue.push(item);

                if (postprocessingTask != null)
                    postprocessingTask.run(item);

                processed++;

            } catch (Exception ex) {
                log.error("Failed to queue an item", ex);
            }
        }

        callback.onComplete(processed, items.size());
    }
}
