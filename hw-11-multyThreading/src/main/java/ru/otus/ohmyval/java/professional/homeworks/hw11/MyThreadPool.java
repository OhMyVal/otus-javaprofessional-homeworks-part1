package ru.otus.ohmyval.java.professional.homeworks.hw11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    private int numThreads;

    public int getNumThreads() {
        return numThreads;
    }
    BlockingQueue<ThreadPoolTask> taskQueue = new ArrayBlockingQueue<>(5000);
    public MyThreadPool(int numThreads) {
        this.numThreads = numThreads;

            for (int i = 0; i < numThreads; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                }).start();

            }
        }

}
