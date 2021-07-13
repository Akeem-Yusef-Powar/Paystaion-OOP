package edu.temple.cis.paystation;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

public class AltOne<localDateTime> implements RateStrategy{

    ProgressiveRate pr;
    LinerRateOne l1;

    localDateTime localDate = (localDateTime) LocalDateTime.now();
    DayOfWeek day = DayOfWeek.from((TemporalAccessor) localDate);
    int condition = day.getValue();// day is returned as int for 1 (monday) to 7(sunday)

    public int calculateTime(int inserted) {

            if (condition <= 5) { // if weekdays
                return pr.calculateTime(inserted);
            } else { // else weekend
                return l1.calculateTime(inserted);
            }
    }
}
