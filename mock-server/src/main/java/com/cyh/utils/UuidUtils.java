package com.cyh.utils;

import java.util.UUID;

/**
 * @auther yanhua.chen
 * @date: 2021/1/28 0028 21:32
 */
public final class UuidUtils {

    public static final String upperNoLine() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    public static final String upperNoLine(int bit) {
        String uuid = upperNoLine();
        if (bit > uuid.length()) {
            // todo
        }
        return uuid.substring(0, bit);
    }


}
