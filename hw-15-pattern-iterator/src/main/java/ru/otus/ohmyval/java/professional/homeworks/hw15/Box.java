package ru.otus.ohmyval.java.professional.homeworks.hw15;

import java.util.Iterator;

public final class Box {
    private final Matryoshka red;        // "red0", "red1", ..., "red9"
    private final Matryoshka green;
    private final Matryoshka blue;
    private final Matryoshka magenta;

    public Box(Matryoshka red, Matryoshka green, Matryoshka blue, Matryoshka magenta) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.magenta = magenta;
    }

    // expected: "red0", "green0", "blue0", "magenta0", "red1", "green1", "blue1", "magenta1",...
    public Iterator<String> getSmallFirstIterator() {
        // TODO
    }

    // expected: "red0", "red1", ..., "red9", "green0", "green1", ..., "green9", ...
    public Iterator<String> getColorFirstIterator() {
        // TODO
    }
}
