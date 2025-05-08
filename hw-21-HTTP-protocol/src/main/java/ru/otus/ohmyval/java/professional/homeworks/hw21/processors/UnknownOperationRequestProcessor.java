package ru.otus.ohmyval.java.professional.homeworks.hw21.processors;

import ru.otus.ohmyval.java.professional.homeworks.hw21.HttpRequest;
import ru.otus.ohmyval.java.professional.homeworks.hw21.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnknownOperationRequestProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 500 Internal Server Error\r\nContent-Type: text/html\r\n\r\n<html><body><h1>INTERNAL SERVER ERROR!!!</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
