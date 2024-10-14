package fun.wilddev.spring.core.interfaces;

import java.util.List;

/**
 * Slicer interface to load a part of larger data set
 *
 * @param <T> - context type
 */
public interface Slicer<T> {

    /**
     * Returns data chunk of specified size
     *
     * @param size - chunk size
     * @return data chunk
     */
    List<T> slice(int size);
}
