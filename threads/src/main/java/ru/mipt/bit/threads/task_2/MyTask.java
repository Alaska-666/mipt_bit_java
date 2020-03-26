package ru.mipt.bit.threads.task_2;

import java.util.concurrent.Callable;

public class MyTask<T> implements Task<T> {
    private final Callable<? extends T> callable;
    private T result = null;

    public MyTask(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override
    public T get() throws Exception {
        if (result != null) return result;
        synchronized (callable) {
            try {
                result = callable.call();
            } catch (RuntimeException runtimeException) {
                throw new CallableException();
            }
            return result;
        }
    }

    private static class CallableException extends Exception {
        CallableException() {
            super("Runtime exception occurred in get method in class MyTask.");
        }
    }
}
