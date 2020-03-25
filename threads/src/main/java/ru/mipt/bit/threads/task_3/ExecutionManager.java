package ru.mipt.bit.threads.task_3;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
