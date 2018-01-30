package com.cyh.commons.apache.lang.time;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

/**
 * Created by yanhuche on 6/13/2016.
 */
public class DateFormatUtilsTest {

    private static final FastDateFormat FAST_DATE_FORMAT_NO_TIME_ZONE = DateFormatUtils.ISO_DATETIME_FORMAT;
    private static final FastDateFormat FAST_DATE_FORMAT_TIME_ZONE = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;
    private static final FastDateFormat FAST_DATE_FORMAT3 = FastDateFormat.getInstance("yyyyMMddHHmm");
    private static final String THE3GPP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static final FastDateFormat FAST_DATE_FORMAT4 = FastDateFormat.getInstance(THE3GPP_DATE_FORMAT);
    private static final String THE3GPP_DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    private static final FastDateFormat FAST_DATE_FORMAT5 = FastDateFormat.getInstance(THE3GPP_DATE_FORMAT_2);


    @Test
    public void test01() throws ParseException {
        String time = "2013-08-02T13:30:00";
        int interval = 10005;
        Date date = FAST_DATE_FORMAT_NO_TIME_ZONE.parse(time);
        date.setTime(date.getTime() + TimeUnit.MINUTES.toMillis(interval));
        time = FAST_DATE_FORMAT_NO_TIME_ZONE.format(date);
        System.out.println(time);
    }

    @Test
    public void test02() throws ParseException {
        String time = "2013-08-02T13:30:00+03:00";
        int interval = 15;
        Date date = FAST_DATE_FORMAT_TIME_ZONE.parse(time);
        date.setTime(date.getTime() + TimeUnit.MINUTES.toMillis(interval));
        time = FAST_DATE_FORMAT_TIME_ZONE.format(date);
        System.out.println(time);
    }

    @Test
    public void test03() throws ParseException {
        System.out.println(FAST_DATE_FORMAT3.parse("201606141335_96"));
        System.out.println(FAST_DATE_FORMAT3.parse("201606141335"));
    }

    @Test
    public void test04() throws ParseException {
        System.out.println(FAST_DATE_FORMAT_TIME_ZONE.format(new Date()));
        System.out.println("a " + FAST_DATE_FORMAT_TIME_ZONE.parse("2013-08-02T13:30:00+03:00").getTime());
        System.out.println("b " + FAST_DATE_FORMAT_TIME_ZONE.parse("2013-08-02T13:30:00+04:00").getTime());
    }

    @Test
    public void test05() {
        Date date = new Date();
        String result = FAST_DATE_FORMAT4.format(date);
        System.out.println(result);
        result = FAST_DATE_FORMAT5.format(date);
        System.out.println(result);
    }



}
