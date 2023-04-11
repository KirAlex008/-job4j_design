package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        List<String> messages = new ArrayList<>();
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean serverState = true;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        messages.add(str);
                        System.out.println(str);
                    }
                    System.out.println(messages.get(0) + " test");
                    if (messages.get(0).contains("Bye")) {
                        out.write("HTTP/1.1 503 OK\r\n\r\n".getBytes());
                        out.write("Server is closed.".getBytes());
                        serverState = false;
                    } else {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    }
                    out.flush();
                    if (!serverState) {
                        server.close();
                    }
                }
            }
        }
    }
}