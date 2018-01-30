package com.cyh.date_time;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yanhuche on 3/30/2017.
 */
public class ClockTest {

    private Clock clock;

    @Before
    public void doBefore() {
        // Get the system clock as UTC offset
        clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }

    @Test
    public void test_LocateDate() {
        // Get the local date and local time
        final LocalDate date = LocalDate.now();
        System.out.println(date);

        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(dateFromClock);
    }

    @Test
    public void test_LocalTime() {
        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        System.out.println(time);

        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(timeFromClock);
    }

    @Test
    public void test_LocalDateTime() {
        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime);

        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetimeFromClock);
    }

    @Test
    public void test_ZoneDateTime() {
        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        System.out.println(zonedDatetime);

        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        System.out.println(zonedDatetimeFromClock);

        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(zonedDatetimeFromZone);
    }

    @Test
    public void test_Duration() {
        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }


}
