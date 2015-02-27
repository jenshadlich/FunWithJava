package de.jeha.fwj.time;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import java.util.Calendar;

public class NextDay {

    public static void main(String... args) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, 3);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.AM_PM, 0);
        c.roll(Calendar.DATE, true);
        System.out.println(c.getTime());

        DateTime d = new DateMidnight().toDateTime().plusDays(1).plusHours(3);

        System.out.println(d.toDate());
    }
}
