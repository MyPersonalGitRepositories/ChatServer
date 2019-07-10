package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerLoader {

    private static ServerSocket server;

    public static void main(String[] args) {

        start();
        handle();
        end();

    }

    private static void start() {
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        // handling of connection of client
        while (true) {
            try {
                Socket client = server.accept();
                new ClientHandler(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
