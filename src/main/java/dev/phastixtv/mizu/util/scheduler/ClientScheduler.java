package dev.phastixtv.mizu.util.scheduler;

import java.util.concurrent.*;

public class ClientScheduler {

    private final ScheduledExecutorService scheduler;

    public ClientScheduler(int poolSize) {
        this.scheduler = Executors.newScheduledThreadPool(poolSize);
    }

    public ScheduledFuture<?> runTaskLater(Runnable task, long delay) {
        return scheduler.schedule(task, delay, TimeUnit.SECONDS);
    }

    public ScheduledFuture<?> runTaskTimer(Runnable task, long delay, long period) {
        return scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.SECONDS);
    }

    public Future<?> runtTask(Runnable task) {
        return scheduler.submit(task);
    }

    public CompletableFuture<?> runTaskAsync(Runnable task) {
        return CompletableFuture.runAsync(() -> {
            final Future<?> scheduledFuture = scheduler.submit(task);
            try {
                scheduledFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, scheduler);
    }

    public CompletableFuture<?> runTaskAsync(Runnable task, long delay) {
        return CompletableFuture.runAsync(() -> {
            final Future<?> scheduledFuture = scheduler.schedule(task, delay, TimeUnit.SECONDS);
            try {
                scheduledFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, scheduler);
    }

    public CompletableFuture<?> runTaskTimerSync(Runnable task, long delay, long period) {
        return CompletableFuture.runAsync(() -> {
            final ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(task, delay, period, TimeUnit.SECONDS);
            try {
                scheduledFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, scheduler);
    }

    public void cancel() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
