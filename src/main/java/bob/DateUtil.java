package bob;

import bob.exception.BobException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    public static void isValidDate(String date) throws BobException {
        LocalDate d;
        try {
            d = LocalDate.parse(date, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new BobException(Errors.INVALID_DATE);
        }
        LocalDate currDate = LocalDate.now();
        if (d.isBefore(currDate)) {
            throw new BobException("date before today is not allowed.");
        }
    }
    public static void isValidEndDate(String startDate, String endDate) throws BobException {
        LocalDate start;
        LocalDate end;
        try {
            start = LocalDate.parse(startDate, DATE_FORMAT);
            end = LocalDate.parse(endDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new BobException(Errors.INVALID_DATE);
        }
        if (end.isBefore(start)) {
            throw new BobException("end date MUST BE AFTER start date.");
        }
    }
    public static void isValidTime(String time) throws BobException {
        try {
            LocalTime.parse(time, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new BobException(Errors.INVALID_TIME);
        }
    }

}
