package fun.wilddev.spring.core.interfaces;

import java.util.List;

public interface Slicer<T> {

    List<T> slice(int size);
}
