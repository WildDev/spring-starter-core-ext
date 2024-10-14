package fun.wilddev.spring.core.interfaces;

import org.springframework.lang.NonNull;

/**
 * Multipurpose queue interface
 *
 * @param <T> - context type
 */
public interface Queue<T> {

    /**
     * Publishes an item to the queue
     *
     * @param item - an object to be published
     * @throws Exception - for exceptional cases. May be absent or narrowed in the implementations
     */
    void push(@NonNull T item) throws Exception;
}
