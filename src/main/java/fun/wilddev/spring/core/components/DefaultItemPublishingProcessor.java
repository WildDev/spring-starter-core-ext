package fun.wilddev.spring.core.components;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

import fun.wilddev.spring.core.interfaces.*;

/**
 * Default implementation for {@link ItemPublishingProcessor}
 *
 * @param <T> - item type
 *
 * @see fun.wilddev.spring.core.abstractions.PublishingPoller
 */
@AllArgsConstructor
public class DefaultItemPublishingProcessor<T> implements ItemPublishingProcessor<T> {

    /**
     * Target queue where sliced items are moved
     */
    protected final Queue<T> queue;

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

    @Override
    public void process(@NonNull T item) throws Exception {

        if (preprocessingTask != null)
            preprocessingTask.run(item);

        queue.push(item);

        if (postprocessingTask != null)
            postprocessingTask.run(item);
    }
}
