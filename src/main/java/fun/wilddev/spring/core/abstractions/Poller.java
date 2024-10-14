package fun.wilddev.spring.core.abstractions;

import java.util.List;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import fun.wilddev.spring.core.interfaces.*;

@AllArgsConstructor
public abstract class Poller<T> {

    protected final Logger log;

    protected final Integer pollSize;

    protected final Slicer<T> slicer;

    protected final Queue<T> queue;

    protected final Task<T> preprocessingTask;

    protected final PollerCallback callback;

    public void poll() {

        List<T> items = slicer.sliceNew(pollSize);

        if (items.isEmpty()) {

            log.debug("No items to process, skipping ...");
            return;
        }

        int processed = 0;

        for (T item : items) {

            try {
                if (preprocessingTask != null)
                    preprocessingTask.run(item);

                queue.push(item);

                processed++;

            } catch (Exception ex) {
                log.error("Failed to queue an item", ex);
            }
        }

        callback.onComplete(processed, items.size());
    }
}
