package ru.otus.ohmyval.java.professional.homeworks.hw21;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpServer {
    private int port;
    private int threadPoolNumber;
    private Dispatcher dispatcher;
    ExecutorService serv;
    Properties properties;

    public HttpServer(String fileName) {
        this.getProperties(fileName);
    }

    public void getProperties(String fileName) {
        properties = new Properties();
        try (InputStream inputStream = HttpServer.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            properties.load(reader);
            port = Integer.parseInt(properties.getProperty("server.port"));
            threadPoolNumber = Integer.parseInt(properties.getProperty("threadPool.number"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        serv = Executors.newFixedThreadPool(threadPoolNumber);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            this.dispatcher = new Dispatcher(this);
            System.out.println("Диспетчер проинициализирован");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    serv.execute(() -> {
                        try {
                            threadPoolTask(socket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        serv.shutdown();
    }

    public void serverShutdown() {
        serv.shutdown();
    }

    private void threadPoolTask(Socket socket) throws IOException {
        byte[] buffer = new byte[8192];
        int n = socket.getInputStream().read(buffer);
        if (n > 0) {
            String rawRequest = new String(buffer, 0, n);
            HttpRequest request = new HttpRequest(rawRequest);
            request.info(true);
            dispatcher.execute(request, socket.getOutputStream());
        }
        socket.close();
    }
}
