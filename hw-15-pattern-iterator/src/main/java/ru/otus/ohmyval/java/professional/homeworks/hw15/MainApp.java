package ru.otus.ohmyval.java.professional.homeworks.hw15;

import java.util.Iterator;

public class MainApp {
    public static void main(String[] args) {
        Box box = new Box();
        box.fillIteratorList();
        printElements(box.GET_SMALL_FIRST);
        printElements(box.GET_COLOR_FIRST);

        System.out.println(box.sumOfElements);

    }

    private static void printElements(Iterator<String> it) {
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }
}
