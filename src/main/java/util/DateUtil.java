package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DateUtil {

    public static boolean isWeekend(final LocalDate localDate) {
        DayOfWeek day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
        return DayOfWeek.SATURDAY.equals(day) || DayOfWeek.SUNDAY.equals(day);
    }

}
