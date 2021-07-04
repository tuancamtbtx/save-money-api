package vn.xteam.savemoneyapi.common.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static String getCurrentDateWithFormat(String format) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(format)
                .withLocale(Locale.US)
                .withZone(ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    public static String getSubDateWithFormat(String format, long days) {
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(format)
                .withLocale(Locale.US)
                .withZone(ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusDays = now.minusDays(days);
        return formatter.format(minusDays);
    }

    public static Long getCurrentTimestamp() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts.getTime();
    }

    public static long getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        return timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);
    }

    public static LocalDate getDateFromTimestamp(Timestamp ts) {
        return ts.toLocalDateTime().toLocalDate();
    }

    public static Period getTimeDiff(Timestamp oldTs, Timestamp newTs) {
        LocalDate start = getDateFromTimestamp(oldTs);
        LocalDate end = getDateFromTimestamp(newTs);
        return Period.between(start, end);
    }

    public static void main(String[] args) {
//        Timestamp test = new Timestamp(System.currentTimeMillis());
//        long dateDiff = getDateDiff(test, test, TimeUnit.DAYS);
        System.out.println((0/12));
    }

}
