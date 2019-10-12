package net.barbux.creatures2d;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ParallelExecutor {
    final ExecutorService executorService;
    final int threadCount;
    ParallelExecutor(int threadCount) {
        this.threadCount = threadCount;
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    <T> void execute(final ArrayList<T> items, Consumer<T> consumer) {
        int size = items.size();
        int current = 0;
        List<Future<?>> futures = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; ++i) {
            final int start = current;
            final int end = (i == threadCount - 1) ? size : current + (size / threadCount);

            futures.add(executorService.submit(() -> {
                for (int j = start; j < end; ++j) {
                    consumer.accept(items.get(j));
                }
            }));

            current = end;
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
