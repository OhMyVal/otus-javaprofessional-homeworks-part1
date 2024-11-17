package ru.otus.ohmyval.java.professional.homeworks.hw11;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    private int numThreads;

    private Boolean shutdown = false;

    public int getNumThreads() {
        return numThreads;
    }

//    BlockingQueue<ThreadPoolTask> queue = new ArrayBlockingQueue<>(5000);

    LinkedList<ThreadPoolTask> list = new LinkedList<>();


    public MyThreadPool(int numThreads) {
        this.numThreads = numThreads;
        for (int i = 0; i < numThreads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!shutdown) {
                            Thread.sleep(10);
//                            ThreadPoolTask task = queue.poll(10000, TimeUnit.MILLISECONDS);
                            ThreadPoolTask task = list.poll();
                            if (task != null) {
                                System.out.println(Thread.currentThread().getName() + " " + task.doWork());
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    public void execute(ThreadPoolTask threadPoolTask) {
        if (!shutdown) {
            list.offer(threadPoolTask);
//            queue.add(threadPoolTask);
        } else {
            throw new IllegalStateException();
        }
    }

    public void shutdown() {
        shutdown = true;
    }
}
