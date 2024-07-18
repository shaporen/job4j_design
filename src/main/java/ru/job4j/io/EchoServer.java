package ru.job4j.io;

import java.io.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    if (string != null && string.contains("msg=")) {
                        String message = string.split(" ")[1];
                        if ("/?msg=Hello".equals(message)) {
                            output.write("Hello".getBytes());
                        } else if ("/?msg=Exit".equals(message)) {
                            server.close();
                        } else {
                            output.write(message.substring("/?msg=".length()).getBytes());
                        }
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception", e);
        }
    }
}
