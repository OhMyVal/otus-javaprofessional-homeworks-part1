package ru.otus.ohmyval.java.professional.homeworks.hw21;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        new HttpServer(8189, Executors.newFixedThreadPool(4)).start();
    }
}
