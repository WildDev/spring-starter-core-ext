package fun.wilddev.spring.core.interfaces;

import org.springframework.lang.NonNull;

/**
 * Multipurpose task interface
 *
 * @param <T> - context type
 */
public interface Task<T> {

    /**
     * Task executor method
     *
     * @param context - task context
     */
    void run(@NonNull T context);
}
