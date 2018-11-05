package com.cyh.commons.apache.lang.time;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

/**
 * Created by CYH
 */
public class DateFormatUtilsTest3 {

    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test01() {
        System.out.println(nullSafeFormat(new Date()));
        System.out.println(nullSafeFormat(null));
    }

    /**
     * return null if date is null
     * @param date
     * @return
     */
    public static String nullSafeFormat(Date date) {
        return Optional.ofNullable(date).map(x -> DATE_FORMAT.format(x)).orElse(null);
    }



}
