package com.cyh.commons.apache.lang.time;

import java.text.ParseException;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

/**
 * Created by yanhuche on 10/10/2016.
 */
public class DateFormatUtilsTest2 {

    private static final FastDateFormat MILLI_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    private static final FastDateFormat ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssX");
    private static final FastDateFormat MILLI_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");

    private static final String MILLI_ZONE_TIME1 = "2014-04-14T19:20:30.453+01:00";
    private static final String MILLI_ZONE_TIME2 = "2014-04-14T19:20:30.453-0200";
    private static final String MILLI_ZONE_TIME3 = "2014-04-14T19:20:30.453Z";

    private static final String ZONE_TIME1 = "2014-04-14T19:20:30+01:00";
    private static final String ZONE_TIME2 = "2014-04-14T19:20:30-0200";
    private static final String ZONE_TIME3 = "2014-04-14T19:20:30Z";

    private static final String MILLI_TIME = "2014-04-14T19:20:30.453";

    private static final String ISO_TIME = "2014-04-14T19:20:30";


    @Test
    public void test01() throws ParseException {
        System.out.println("a " + MILLI_ZONE_FORMAT.parse(MILLI_ZONE_TIME1));
        System.out.println("b " + MILLI_ZONE_FORMAT.parse(MILLI_ZONE_TIME2));
        System.out.println("c " + MILLI_ZONE_FORMAT.parse(MILLI_ZONE_TIME3));
    }

    @Test
    public void test02() throws ParseException {
        System.out.println("a " + ZONE_FORMAT.parse(ZONE_TIME1));
        System.out.println("b " + ZONE_FORMAT.parse(ZONE_TIME2));
        System.out.println("c " + ZONE_FORMAT.parse(ZONE_TIME3));
    }

    @Test
    public void test03() throws ParseException {
        System.out.println("a " + MILLI_FORMAT.parse(MILLI_TIME));
    }

    @Test
    public void test04() throws ParseException {
        System.out.println("a " + ISO_DATETIME_FORMAT.parse(ISO_TIME));
    }


    @Test(expected = ParseException.class)
    public void test_MILLI_ZONE_FORMAT_1() throws ParseException {
        MILLI_ZONE_FORMAT.parse(ZONE_TIME1);
    }

    @Test(expected = ParseException.class)
    public void test_MILLI_ZONE_FORMAT_2() throws ParseException {
        MILLI_ZONE_FORMAT.parse(MILLI_TIME);
    }

    @Test(expected = ParseException.class)
    public void test_MILLI_ZONE_FORMAT_3() throws ParseException {
        MILLI_ZONE_FORMAT.parse(ISO_TIME);
    }


    @Test(expected = ParseException.class)
    public void test_ZONE_FORMAT_1() throws ParseException {
        ZONE_FORMAT.parse(MILLI_ZONE_TIME1);
    }

    @Test(expected = ParseException.class)
    public void test_ZONE_FORMAT_2() throws ParseException {
        ZONE_FORMAT.parse(MILLI_TIME);
    }

    @Test(expected = ParseException.class)
    public void test_ZONE_FORMAT_3() throws ParseException {
        ZONE_FORMAT.parse(ISO_TIME);
    }


    @Test
    public void test_MILLI_FORMAT_1() throws ParseException {
        MILLI_FORMAT.parse(MILLI_ZONE_TIME1);
    }

    @Test(expected = ParseException.class)
    public void test_MILLI_FORMAT_2() throws ParseException {
        MILLI_FORMAT.parse(ZONE_TIME1);
    }

    @Test(expected = ParseException.class)
    public void test_MILLI_FORMAT_3() throws ParseException {
        MILLI_FORMAT.parse(ISO_TIME);
    }


    @Test
    public void test_ISO_DATETIME_FORMAT_1() throws ParseException {
        ISO_DATETIME_FORMAT.parse(MILLI_ZONE_TIME1);
    }

    @Test
    public void test_ISO_DATETIME_FORMAT_2() throws ParseException {
        ISO_DATETIME_FORMAT.parse(ZONE_TIME1);
    }

    @Test
    public void test_ISO_DATETIME_FORMAT_3() throws ParseException {
        ISO_DATETIME_FORMAT.parse(MILLI_TIME);
    }

}
