package fun.wilddev.spring.core.interfaces;

/**
 * Callback interface for {@link fun.wilddev.spring.core.abstractions.PublishingPoller}
 */
@FunctionalInterface
public interface PublishingPollerCallback {

    /**
     * Triggers whenever the related action is completed
     *
     * @param processed - successfully processed items count
     * @param total - total items count
     */
    void onComplete(int processed, int total);
}
