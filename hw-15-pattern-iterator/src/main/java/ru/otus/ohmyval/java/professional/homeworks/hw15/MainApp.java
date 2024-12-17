package ru.otus.ohmyval.java.professional.homeworks.hw15;

import java.util.Iterator;

public class MainApp {
    public static void main(String[] args) {
        Box box = new Box();
        printElements(box.getColorFirstIterator());
        printElements(box.getSmallFirstIterator());
    }

    private static void printElements(Iterator<String> it) {
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }
}
