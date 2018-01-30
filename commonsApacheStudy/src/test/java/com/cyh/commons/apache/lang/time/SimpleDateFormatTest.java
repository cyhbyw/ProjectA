package com.cyh.commons.apache.lang.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Created by yanhuche on 5/12/2017.
 */
public class SimpleDateFormatTest {

    @Test
    public void test01() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println(dateFormat.format(new Date()));

        Date date = dateFormat.parse("2011-02-01T23:31:05.123+14:30");
        System.out.println(translateCalendarToSnmpFormat(date));
    }

    public static String translateCalendarToSnmpFormat(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d,HH:mm:ss.SSSZ");// 2013-05-02
        // 15:47:15.662+0800
        String tmpStr = format.format(time);
        String timePart = tmpStr.substring(0, tmpStr.indexOf('.') + 2);// 2013-05-02
        // 15:47:15.6
        String totalZone = tmpStr.substring(tmpStr.indexOf('.') + 4);
        String zoneSign = totalZone.substring(0, 1);
        String zone = totalZone.substring(1);
        try {
            zone = new SimpleDateFormat("H:m").format(new SimpleDateFormat("HHmm").parse(zone));
        } catch (ParseException e) {
            return null;
        }
        return timePart + ',' + zoneSign + zone;
    }


}
