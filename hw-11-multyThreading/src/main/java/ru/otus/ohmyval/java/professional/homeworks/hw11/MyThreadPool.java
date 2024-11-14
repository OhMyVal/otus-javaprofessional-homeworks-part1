package ru.otus.ohmyval.java.professional.homeworks.hw11;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    private int numThreads;

    private Boolean shutdown = false;

    public int getNumThreads() {
        return numThreads;
    }

    BlockingQueue<ThreadPoolTask> queue = new ArrayBlockingQueue<>(5000);


    public MyThreadPool(int numThreads) {
        this.numThreads = numThreads;
        for (int i = 0; i < numThreads; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!shutdown) {
                            Thread.sleep(10);
                            ThreadPoolTask task = queue.take();
                            System.out.println(Thread.currentThread().getName() + " " + task.doWork());
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
            }).start();

        }
    }

    public Boolean execute(ThreadPoolTask threadPoolTask){
        queue.add(threadPoolTask);
        return true;
    }

    public void shutdown(){
        shutdown = true;
    }

}
