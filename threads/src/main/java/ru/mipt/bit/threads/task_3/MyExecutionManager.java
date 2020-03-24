package ru.mipt.bit.threads.task_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class MyExecutionManager implements ExecutionManager {

    @Override
    public Context execute(Runnable callback, Runnable... tasks){
        List<Thread> threads = new ArrayList<>();
        MyContext context = new MyContext();

        Thread mainThread = new Thread(() -> {
            for (Runnable task : tasks) {
                if (!context.isFinished()) {
                    Thread thread = new Thread(task);
                    threads.add(thread);
                    thread.start();
                }
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                    context.completedTaskCount.incrementAndGet();
                } catch (InterruptedException ignored) {
                    context.failedTaskCount.incrementAndGet();
                }
                context.interruptedTaskCount.decrementAndGet();
            }
            callback.run();
            context.isFinished.set(true);
        });

        context.interruptedTaskCount.set(tasks.length);
        mainThread.start();
        return context;
    }

    private static class MyContext implements Context {
        private AtomicInteger completedTaskCount = new AtomicInteger(0);
        private AtomicInteger failedTaskCount = new AtomicInteger(0);
        private AtomicInteger interruptedTaskCount = new AtomicInteger(0);
        private AtomicBoolean isFinished = new AtomicBoolean(false);

        @Override
        public int getCompletedTaskCount() {
            return completedTaskCount.get();
        }

        @Override
        public int getFailedTaskCount() {
            return failedTaskCount.get();
        }

        @Override
        public int getInterruptedTaskCount() {
            return interruptedTaskCount.get();
        }

        @Override
        public void interrupt() {
            this.isFinished.set(true);
        }

        @Override
        public boolean isFinished() {
            return isFinished.get();
        }
    }
}
