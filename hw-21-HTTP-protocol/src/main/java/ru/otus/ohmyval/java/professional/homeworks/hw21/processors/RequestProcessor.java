package ru.otus.ohmyval.java.professional.homeworks.hw21.processors;

import ru.otus.ohmyval.java.professional.homeworks.hw21.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest httpRequest, OutputStream output) throws IOException;
}
