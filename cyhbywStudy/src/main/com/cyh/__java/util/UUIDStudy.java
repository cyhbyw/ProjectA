package com.cyh.__java.util;

import java.util.UUID;

/**
 * Created by yanhuche on 7/26/2016.
 */
public class UUIDStudy {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid + "  " + uuid.length());
        }
    }



}
