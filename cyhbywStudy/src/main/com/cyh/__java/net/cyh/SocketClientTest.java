package com.cyh.__java.net.cyh;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by yanhuche on 12/21/2016.
 */
public class SocketClientTest {

    public static void main(String[] args) throws IOException {
        SocketClientTest main = new SocketClientTest();
        main.run();
    }

    private void run() throws IOException {
        Socket socket = new Socket("localhost", 30001);
        socket.setSoTimeout(2000);
        InputStream inputStream = socket.getInputStream();

        int a = 1;
        if (a == 1) {
            while (true) {
                try {
                    int value = inputStream.read();
                    System.out.println("value: " + value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        inputStream.close();
        socket.close();
    }


}
