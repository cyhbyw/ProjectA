package com.cyh.date_time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;

/**
 * LocalDateTime <--> Long 相互转换
 * @author: yanhua.chen
 * @date: 2019/1/22 11:09
 */
public class LocalDateTimeLongConvertTest {

    @Test
    public void test1() {
        System.out.println(toLocalDateTime(1548126816138L));
    }

    /**
     * long (milli second) ----> LocalDateTime
     * @param time
     * @return
     */
    private LocalDateTime toLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    @Test
    public void test2() {
        System.out.println(toLong(LocalDateTime.now()));
    }

    /**
     * LocalDateTime ----> long (milli second)
     * @param time
     * @return
     */
    private long toLong(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
