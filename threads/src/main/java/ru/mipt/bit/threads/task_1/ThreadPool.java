package ru.mipt.bit.threads.task_1;

public interface ThreadPool {
        void start();

        void execute(Runnable runnable);
}
