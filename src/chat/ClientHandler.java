package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
        start();
    }


    @Override
    public void run() {
        while (true) {
            readDate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readDate() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0)
                return;
            short id = dis.readShort();
            // read packet

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
