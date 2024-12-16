package ru.otus.ohmyval.java.professional.homeworks.hw11;


import java.util.LinkedList;

public class MyThreadPool {
    private int numThreads;

    private Boolean shutdown = false;
    private Object monitor;
    private ThreadPoolTask task;

    public int getNumThreads() {
        return numThreads;
    }

    private LinkedList<ThreadPoolTask> list = new LinkedList<>();


    public MyThreadPool(int numThreads, Object monitor) {
        this.numThreads = numThreads;
        this.monitor = monitor;
        for (int i = 0; i < numThreads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!shutdown) {
                            synchronized (monitor) {
                                task = list.poll();
                                if (task == null) {
                                    monitor.wait();
                                }
                            }
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
            synchronized (monitor) {
                list.offer(threadPoolTask);
                monitor.notify();
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void shutdown() {
        shutdown = true;
    }
}
