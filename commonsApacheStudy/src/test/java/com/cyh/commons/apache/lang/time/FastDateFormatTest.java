package com.cyh.commons.apache.lang.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by yanhuche on 7/13/2016.
 */
public class FastDateFormatTest {

    private static final FastDateFormat MILLI_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd-'T'HH:mm:ss.SSSX");

    @Test
    public void test_A() throws ParseException {
        Date date = MILLI_ZONE_FORMAT.parse("2016-03-27-T01:00:00.000+0200");
        System.out.println(date + "  " + date.getTime());

        date = MILLI_ZONE_FORMAT.parse("2016-10-30-T01:00:00.000+0300");
        System.out.println(date + "  " + date.getTime());


    }

    @Test
    public void test_B() throws ParseException {
        Date date = MILLI_ZONE_FORMAT.parse("2016-01-01-T03:05:54.225+0300");
        System.out.println(date + "  " + date.getTime());

        date = MILLI_ZONE_FORMAT.parse("2016-10-30-T04:00:00.000+0300");
        System.out.println(date + "  " + date.getTime());

        date = MILLI_ZONE_FORMAT.parse("2016-12-05-T15:00:00.000+0800");
        System.out.println(date + "  " + date.getTime());


        System.out.println(new Date() + "  " + System.currentTimeMillis());

    }

    @Test
    public void test_C() {
        String result = MILLI_ZONE_FORMAT.format(1489994220000L);
        System.out.println(result);
    }


}
