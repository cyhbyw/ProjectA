package com.cyh.commons.apache.lang.time;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yanhuche on 11/8/2016.
 */
public class FastDateFormatTest2 {

    private static final FastDateFormat MILLI_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd-'T'HH:mm:ssXXX");
    private static final FastDateFormat DATE_FORMAT_FOR_MINUTE = FastDateFormat.getInstance("yyyyMMddHHmm");
    private static final String[] args = new String[] {"2016-10-30-T03:00:22.429+0300",
            "2016-10-30-T03:01:22.339+0300", "2016-10-30-T03:59:09.178+0300", "2016-10-30-T03:59:21.595+0300",
            "2016-10-30-T03:00:22.429+0200", "2016-10-30-T03:01:22.339+0200", "2016-10-30-T03:59:09.178+0200",
            "2016-10-30-T03:59:21.595+0200"};


    @Test
    public void test_01() throws ParseException {
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            System.out.println(String.format("args[%d]=%s, format result: %s", argIndex, args[argIndex],
                    MILLI_ZONE_FORMAT.parse(args[argIndex]).toString()));
        }
    }

    @Test
    public void test_02() throws ParseException {
        System.out.println(Calendar.getInstance());
    }

    @Test
    public void test_03() {
        String format = MILLI_ZONE_FORMAT.format(new Date());
        System.out.println(format);
    }


}
