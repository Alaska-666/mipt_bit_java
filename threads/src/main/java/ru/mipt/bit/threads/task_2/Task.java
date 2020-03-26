package ru.mipt.bit.threads.task_2;

public interface Task<T> {
    public T get() throws Exception;
}
