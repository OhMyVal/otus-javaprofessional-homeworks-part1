package ru.otus.ohmyval.java.professional.homeworks.hw11;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private int numThreads;

    private Boolean shutdown = false;
    private Object monitor;

    public int getNumThreads() {
        return numThreads;
    }

    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
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
                                    ThreadPoolTask task = list.poll();
                                    if (task == null) {
                                        monitor.wait(100);
                                    } else {
                                        monitor.notify();
                                        System.out.println(Thread.currentThread().getName() + " " + task.doWork());
                                    }
                                }
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


//                    try {
//                        while (!shutdown) {
//                            reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
//                            ThreadPoolTask task = list.poll();
//                            while (task == null)
//                                condition.await(1000, TimeUnit.MILLISECONDS);
//                            System.out.println(Thread.currentThread().getName() + " " + task.doWork());
//                            condition.signal();
//                            reentrantLock.unlock();
//                        }
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }

//                    try {
//                        while (!shutdown) {
//                            reentrantLock.tryLock(10000, TimeUnit.MILLISECONDS);
//                            ThreadPoolTask task = list.poll();
//                            if (task != null) {
//                                System.out.println(Thread.currentThread().getName() + " " + task.doWork());
//                            }
//                            reentrantLock.unlock();
//                        }
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                }
            }).start();
        }
    }

    public void execute(ThreadPoolTask threadPoolTask) {
        if (!shutdown) {
            synchronized (monitor) {
                list.offer(threadPoolTask);
            }
        } else {
            throw new IllegalStateException();
        }
    }

//    public void execute(ThreadPoolTask threadPoolTask) throws InterruptedException {
//        if (!shutdown) {
//            reentrantLock.tryLock(1000, TimeUnit.MILLISECONDS);
//            list.offer(threadPoolTask);
//            reentrantLock.unlock();
//        } else {
//            throw new IllegalStateException();
//        }
//    }


    public void shutdown() {
        shutdown = true;
    }
}
