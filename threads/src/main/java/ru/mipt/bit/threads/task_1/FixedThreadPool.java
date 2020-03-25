package ru.mipt.bit.threads.task_1;

import java.util.*;

public class FixedThreadPool implements ThreadPool {
    private final Collection<Thread> threads;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private volatile boolean isFinished = false;

    public FixedThreadPool(int numThreads) {
        Runnable func = () -> {
            while (!isFinished) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            wait();
                        } catch (InterruptedException ignored) {}
                    }
                    tasks.poll().run();
                }
            }
        };
        this.threads = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
             threads.add(new Thread(func));
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        tasks.add(runnable);
        notify();
    }
}
