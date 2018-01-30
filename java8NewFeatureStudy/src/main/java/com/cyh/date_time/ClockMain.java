package com.cyh.date_time;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by yanhuche on 3/29/2017.
 */
public class ClockMain {


    public static void main(String[] args) {
        new ClockMain().run();
    }

    private void run() {
        // Get the system clock as UTC offset 
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        // Get the local date and local time
        final LocalDate date = LocalDate.now();
        System.out.println(date);

        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(dateFromClock);

        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        System.out.println(time);

        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(timeFromClock);

        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime);

        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetimeFromClock);

        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        System.out.println(zonedDatetime);

        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        System.out.println(zonedDatetimeFromClock);

        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(zonedDatetimeFromZone);

        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());
    }


}
