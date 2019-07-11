package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLoader {

    private static ServerSocket server;
    private static ServerHandler handler;
    static Map<Socket, ClientHandler> handlers = new HashMap<>();

    public static void main(String[] args) {

        start();
        handle();
        end();

    }

    private static void start() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle() {
        handler = new ServerHandler(server);
        handler.start();
        readChat();
    }

    private static void readChat() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                if (line.equals("/end"))
                    end();
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ServerHandler getHandler() {
        return handler;
    }

    public static void setHandler(ServerHandler handler) {
        ServerLoader.handler = handler;
    }

    public static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static ClientHandler getHandler(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }

}
