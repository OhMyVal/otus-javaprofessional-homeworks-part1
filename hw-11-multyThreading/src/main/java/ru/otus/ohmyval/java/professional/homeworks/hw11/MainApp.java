package ru.otus.ohmyval.java.professional.homeworks.hw11;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {

        MyThreadPool myThreadPool = new MyThreadPool(4);

        for (int i = 0; i < 100; i++) {
            myThreadPool.execute(new ThreadPoolTask(i));
        }
        Thread.sleep(10000);

        myThreadPool.execute(new ThreadPoolTask(3000));

        myThreadPool.shutdown();

//        myThreadPool.execute(new ThreadPoolTask(7000));

        System.out.println(myThreadPool.getNumThreads());
    }
}
