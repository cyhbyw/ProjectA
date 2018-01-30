package com.cyh.__java.net;

import java.net.Socket;
import java.util.Date;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 30001);
            socket.setKeepAlive(true);
            while (true && null != socket) {
                Thread.sleep(1 * 1000);
                socket.sendUrgentData(65);
                System.out.println(new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
