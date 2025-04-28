package ru.otus.ohmyval.java.professional.homeworks.hw21.processors;

import ru.otus.ohmyval.java.professional.homeworks.hw21.HttpRequest;
import ru.otus.ohmyval.java.professional.homeworks.hw21.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ServerShutdownRequestProcessor implements RequestProcessor {
    private HttpServer httpServer;
    @Override
    public void execute(HttpRequest httpRequest, OutputStream output) throws IOException {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Server is shutdown!!!</h1></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
        httpServer.serverShutdown();
    }
}
