package ru.otus.ohmyval.java.professional.homeworks.hw15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box {
    private Matryoshka red = new Matryoshka(List.of("red0", "red1", "red2", "red3", "red4", "red5", "red6", "red7", "red8", "red9"));
    private Matryoshka green = new Matryoshka(List.of("green0", "green1", "green2", "green3", "green4", "green5", "green6", "green7", "green8", "green9"));
    private Matryoshka blue = new Matryoshka(List.of("blue0", "blue1", "blue2", "blue3", "blue4", "blue5", "blue6", "blue7", "blue8", "blue9"));
    private Matryoshka magenta = new Matryoshka(List.of("magenta0", "magenta1", "magenta2", "magenta3", "magenta4", "magenta5", "magenta6", "magenta7", "magenta8", "magenta9"));

    public Iterator<String> getSmallFirstIterator() {

        List<Iterator<String>> iteratorList = new ArrayList<>();

        iteratorList.add(red.getItems().iterator());
        iteratorList.add(green.getItems().iterator());
        iteratorList.add(blue.getItems().iterator());
        iteratorList.add(magenta.getItems().iterator());

        Iterator<String> GET_SMALL_FIRST = new Iterator<>() {

            int currentIteratorIndex = -1;
            int countAllElements = 0;
            int sumOfElements = red.getItems().size() + green.getItems().size() + blue.getItems().size() + magenta.getItems().size();

            @Override
            public boolean hasNext() {
                return countAllElements < sumOfElements;
            }

            @Override
            public String next() {
                if (currentIteratorIndex >= (iteratorList.size() - 1)) {
                    currentIteratorIndex = -1;
                }
                currentIteratorIndex++;
                countAllElements++;
                return iteratorList.get(currentIteratorIndex).next();
            }
        };
        return GET_SMALL_FIRST;
    }

    public Iterator<String> getColorFirstIterator() {

        List<Iterator<String>> iteratorList = new ArrayList<>();

        iteratorList.add(red.getItems().iterator());
        iteratorList.add(green.getItems().iterator());
        iteratorList.add(blue.getItems().iterator());
        iteratorList.add(magenta.getItems().iterator());

        Iterator<String> GET_COLOR_FIRST = new Iterator<>() {

            int currentIteratorIndex = -1;
            int i = 0;
            int countAllElements = 0;
            int sumOfElements = red.getItems().size() + green.getItems().size() + blue.getItems().size() + magenta.getItems().size();

            @Override
            public boolean hasNext() {
                return countAllElements < sumOfElements;
            }

            @Override
            public String next() {
                if (currentIteratorIndex >= (red.getItems().size() - 1)) {
                    currentIteratorIndex = -1;
                    i++;
                }
                currentIteratorIndex++;
                countAllElements++;
                return iteratorList.get(i).next();
            }
        };
        return GET_COLOR_FIRST;
    }
}
