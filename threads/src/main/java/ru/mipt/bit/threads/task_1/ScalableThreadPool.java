package ru.mipt.bit.threads.task_1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {
    private final Collection<Thread> threads;
    private final Queue<Runnable> tasks = new ArrayDeque<>();
    private final int minNumThreads;
    private final int maxNumThreads;
    private final Runnable func;
    private volatile boolean isFinished = false;

    public ScalableThreadPool(int minNumThreads, int maxNumThreads) {
        this.minNumThreads = minNumThreads;
        this.maxNumThreads = maxNumThreads;
        this.threads = new ArrayList<>();
        this.func = () -> {
            while (!isFinished) {
                Runnable task;
                synchronized (tasks) {
                    if (tasks.isEmpty() && this.threads.size() > this.minNumThreads) {
                        threads.remove(Thread.currentThread());
                        return;
                    }
                    while (tasks.isEmpty()) {
                        try {
                            wait();
                        } catch (InterruptedException ignored) {}
                    }
                    task = tasks.poll();
                }
                try {
                    task.run();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        };

        for (int i = 0; i < this.minNumThreads; i++) {
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

        if (!tasks.isEmpty() && threads.size() < this.maxNumThreads){
            Thread thread = new Thread(func);
            thread.start();
            threads.add(thread);
        } else {
            notify();
        }
    }
}
