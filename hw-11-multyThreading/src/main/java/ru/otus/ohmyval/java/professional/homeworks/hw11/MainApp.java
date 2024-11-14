package ru.otus.ohmyval.java.professional.homeworks.hw11;

public class MainApp {
    public static void main(String[] args) {

    MyThreadPool myThreadPool = new MyThreadPool(4);

        System.out.println(myThreadPool.getNumThreads());
    }
}
