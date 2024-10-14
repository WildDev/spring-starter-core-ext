package fun.wilddev.spring.core.interfaces;

import org.springframework.lang.NonNull;

public interface Task<T> {

    void run(@NonNull T ref);
}
