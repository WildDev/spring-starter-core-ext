package fun.wilddev.spring.core.interfaces;

@FunctionalInterface
public interface PollerCallback {

    void onComplete(int processed, int total);
}
