package fun.wilddev.spring.core.interfaces;

@FunctionalInterface
public interface PublishingPollerCallback {

    void onComplete(int processed, int total);
}
