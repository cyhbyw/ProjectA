package com.cyh.date_time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * @author: CYH
 * @date: 2018/12/22 0022 18:33
 */
public class LocalDateTimeMethodTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test01_format() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_OFFSET_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_ORDINAL_DATE));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_WEEK_DATE));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        //System.out.println(LocalDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
    }

    @Test
    public void test02_format() {
        System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }

    @Test
    public void test03_parse() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-12-22 18:41:07", DATE_TIME_FORMATTER);
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(DATE_TIME_FORMATTER));
    }

    @Test
    public void test04_minus() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
        System.out.println(localDateTime);
    }

    @Test
    public void test05_plus() {
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(24);
        System.out.println(localDateTime);
    }

    @Test
    public void test06_beforeAfter() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-12-22 18:41:07", DATE_TIME_FORMATTER);
        System.out.println(localDateTime);
        System.out.println(localDateTime.isBefore(LocalDateTime.now()));
    }

    @Test
    public void test07_isEqual() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-12-22 18:41:07", DATE_TIME_FORMATTER);
        LocalDateTime localDateTime2 = LocalDateTime.parse("2018-12-22 18:41:07", DATE_TIME_FORMATTER);
        // 比较的是时间，只要时间相同就返回True
        System.out.println(localDateTime2.isEqual(localDateTime));

    }

}

