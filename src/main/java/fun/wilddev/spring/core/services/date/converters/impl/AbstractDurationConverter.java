package fun.wilddev.spring.core.services.date.converters.impl;

import fun.wilddev.spring.core.services.date.DurationValue;
import fun.wilddev.spring.core.services.date.converters.DurationConverter;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.slf4j.Logger;
import org.springframework.lang.NonNull;

import java.util.*;
import lombok.*;

/**
 * An abstraction for the duration converter implementations
 */
public abstract class AbstractDurationConverter implements DurationConverter {

    /**
     * Logger reference
     */
    protected final Logger log;

    /**
     * Comparator instance for {@code index} setup
     */
    protected final Comparator<TimeUnit> blockComparator;

    /**
     * An index for quicker access of the starter time unit
     */
    protected final Map<TimeUnit, BlockNode> index;

    /**
     * Instantiates the class by composite params set
     *
     * @param log - logger reference
     * @param blockComparator - comparator instance for {@code index} setup
     * @param nanoSecondsFunc - converter function for {@link TimeUnit#NANOSECONDS}
     * @param microSecondsFunc - converter function for {@link TimeUnit#MICROSECONDS}
     * @param milliSecondsFunc - converter function for {@link TimeUnit#MILLISECONDS}
     * @param secondsFunc - converter function for {@link TimeUnit#SECONDS}
     * @param minutesFunc - converter function for {@link TimeUnit#MINUTES}
     * @param hoursFunc - converter function for {@link TimeUnit#HOURS}
     * @param daysFunc - converter function for {@link TimeUnit#DAYS}
     */
    protected AbstractDurationConverter(Logger log, Comparator<TimeUnit> blockComparator,
                                        Function<Integer, Integer> nanoSecondsFunc,
                                        Function<Integer, Integer> microSecondsFunc,
                                        Function<Integer, Integer> milliSecondsFunc,
                                        Function<Integer, Integer> secondsFunc,
                                        Function<Integer, Integer> minutesFunc,
                                        Function<Integer, Integer> hoursFunc,
                                        Function<Integer, Integer> daysFunc) {

        this.log = log;
        this.blockComparator = blockComparator;

        this.index = indexBlocks(new Block[] {
                new Block(TimeUnit.NANOSECONDS, nanoSecondsFunc),
                new Block(TimeUnit.MICROSECONDS, microSecondsFunc),
                new Block(TimeUnit.MILLISECONDS, milliSecondsFunc),
                new Block(TimeUnit.SECONDS, secondsFunc),
                new Block(TimeUnit.MINUTES, minutesFunc),
                new Block(TimeUnit.HOURS, hoursFunc),
                new Block(TimeUnit.DAYS, daysFunc)
        });
    }

    /**
     * Tuple class for {@code timeUnit-func} association
     */
    @Getter
    protected static class Block {

        /**
         * Context time unit
         */
        protected final TimeUnit timeUnit;

        /**
         * Convertor function reference
         */
        protected final Function<Integer, Integer> func;

        /**
         * Instantiates the class by composite params set
         *
         * @param timeUnit - context time unit
         * @param func - converter function reference
         */
        public Block(TimeUnit timeUnit, Function<Integer, Integer> func) {

            this.timeUnit = timeUnit;
            this.func = func;
        }

        /**
         * Copying constructor
         *
         * @param source - source object
         */
        public Block(Block source) {

            this.timeUnit = source.timeUnit;
            this.func = source.func;
        }

        /**
         * Applies {@code func} to the duration value
         *
         * @param value - a duration value
         * @return converted duration value
         */
        public Integer run(@NonNull Integer value) {
            return func.apply(value);
        }
    }

    /**
     * Wrapper class for {@code Block} to achieve linked-list functionality
     */
    @Getter
    protected static class BlockNode extends Block {

        /**
         * Next node reference
         */
        protected final BlockNode next;

        /**
         * Instantiates the class by composite params set
         *
         * @param block - context object
         * @param next - next node reference
         */
        public BlockNode(Block block, BlockNode next) {

            super(block);
            this.next = next;
        }
    }

    /**
     * Assembles an index for the quicker access of the starter time unit
     *
     * @param blocks - input blocks (order important)
     * @return index object
     */
    protected Map<TimeUnit, BlockNode> indexBlocks(@NonNull Block[] blocks) {

        BlockNode head = null;
        Map<TimeUnit, BlockNode> target = new HashMap<>();

        Arrays.sort(blocks, Comparator.comparing(Block::getTimeUnit, Objects.requireNonNull(blockComparator)));

        for (int i = blocks.length - 1; i >= 0; i--) {

            final Block block = blocks[i];

            head = head == null ? new BlockNode(block, null) : new BlockNode(block, head);
            target.put(block.timeUnit, head);
        }

        return target;
    }

    @Override
    public DurationValue convert(@NonNull DurationValue duration, @NonNull TimeUnit target) {

        BlockNode node = index.get(duration.timeUnit());
        Integer value = duration.value();

        while ((node = node.next) != null) {

            val timeUnit = node.timeUnit;
            value = node.run(value);

            if (timeUnit == null) {

                log.error("Unmapped time unit {}", target);
                return duration;

            } else if (timeUnit == target)
                break;
        }

        return new DurationValue(value, target);
    }
}
