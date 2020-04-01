package ru.mipt.bit.threads.task_2;

import java.util.concurrent.Callable;

public class MyTask<T> implements Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result = null;

    public MyTask(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override
    public T get() throws Exception {
        synchronized (callable) {
            if (result != null) return result;
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
