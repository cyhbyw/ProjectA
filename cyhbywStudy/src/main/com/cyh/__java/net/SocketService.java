package com.cyh.__java.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SocketService {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            SocketAddress address = new InetSocketAddress("localhost", 30001);
            serverSocket.bind(address);
            Socket socket = serverSocket.accept();
            new Thread(new T(socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class T implements Runnable {

    private Socket socket = null;

    public T(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(socket.toString());
            socket.setKeepAlive(true);
            socket.setSoTimeout(5 * 1000);
            String _pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(_pattern);
            while (true) {
                System.out.println("begin: " + format.format(new Date()));
                try {
                    InputStream inputStream = socket.getInputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    int data = -1;
                    while ((data = inputStream.read()) != -1) {
                        System.out.println("data: " + data);
                        byteArrayOutputStream.write(data);
                    }
                    System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
                } catch (Exception e) {
                    System.out.println("Exception");
                    e.printStackTrace();
                }
                Thread.sleep(1000);
                System.out.println(socket.isBound()); // ????
                System.out.println(socket.isClosed()); // ????
                System.out.println(socket.isConnected()); // ????
                System.out.println(socket.isInputShutdown()); // ???????
                System.out.println(socket.isOutputShutdown()); // ???????
                System.out.println("end: " + format.format(new Date()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
