package com.cyh.__java.net.cyh;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 12/21/2016.
 */
public class SocketServerTest {

    public static void main(String[] args) throws Exception {
        SocketServerTest main = new SocketServerTest();
        main.run();
    }

    private void run() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress address = new InetSocketAddress("localhost", 30001);
        serverSocket.bind(address);
        Socket socket = serverSocket.accept();
        System.out.println("accepted " + new Date());
        OutputStream outputStream = socket.getOutputStream();

        int testCount = 50;
        while (testCount-- > 0) {
            int sleepSecond = (int) (Math.random() * 5);
            System.out.println("sleepSecond: " + sleepSecond + " " + new Date());
            TimeUnit.SECONDS.sleep(sleepSecond);
            System.out.println("sleep end: " + sleepSecond + " " + new Date());
            outputStream.write(65);
            outputStream.flush();
        }

        outputStream.close();
        socket.close();
        serverSocket.close();
    }


}
