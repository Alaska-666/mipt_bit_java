package ru.mipt.bit.threads.task_2;

import java.util.concurrent.Callable;

public class TaskWithoutAlienMethod<T> implements Task<T> {
    private final Callable<? extends T> callable;
    private volatile T result = null;
    private volatile CallableException exception = null;
    private volatile Status status = Status.EBSONT;

    public TaskWithoutAlienMethod(Callable<? extends T> callable) {
        this.callable = callable;
    }

    @Override
    public T get() throws Exception {
        Status localStatus;
        synchronized (this) {
            localStatus = status;
            if (status.equals(Status.EBSONT)) {
                status = Status.CALCULATING;
            }
        }

        if (localStatus.equals(Status.DONE)) {
            if (result != null) {
                return result;
            } else {
                throw exception;
            }
        }

        while (localStatus.equals(Status.CALCULATING)) {
            this.wait();
            synchronized (this) {
                localStatus = status;
            }
        }

        if (localStatus.equals(Status.DONE)) {
            if (result != null) {
                return result;
            } else {
                throw exception;
            }
        }

        try {
            T localResult = callable.call();
            synchronized (this) {
                result = localResult;
            }
            return result;
        } catch (Exception e) {
            CallableException localException = new CallableException();
            synchronized (this) {
                exception = localException;
            }
            throw exception;
        } finally {
            synchronized (this) {
                status = Status.DONE;
                this.notifyAll();
            }
        }
    }

    private static class CallableException extends Exception {
        CallableException() {
            super("Runtime exception occurred in get method in class MyTask.");
        }
    }
}
