package com.cyh.__java.net;

import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 30001);
            socket.setKeepAlive(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}