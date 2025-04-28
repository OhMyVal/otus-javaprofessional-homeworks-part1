package ru.otus.ohmyval.java.professional.homeworks.hw21;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    ExecutorService serv;

    public HttpServer(int port, ExecutorService serv) {
        this.port = port;
        this.serv = serv;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            this.dispatcher = new Dispatcher();
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
