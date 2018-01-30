package com.cyh.clock;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * Created by yanhuche on 4/1/2017.
 */
public class ClockTest01 {


    @Test
    public void test_clock_01() {
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant); // legacy java.util.Date
        System.out.println(legacyDate);
    }


}
