package bob;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.exception.BobException;

/**
 * Formats datetime and checks the validity of date and time formats
 */
public class DateUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Checks if dates given by the user are the right format
     * @param date date given by the user
     * @throws BobException if:
     *      <ul>
     *          <li>date not in the format, d/M/yy</li>
     *          <li>date before today is entered for deadline tasks</li>
     *      </ul>
     */
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
    /**
     * Checks if dates given by the user are the right format
     * @param startDate start date of event given by the user
     * @param endDate end date if event given by the user
     * @throws BobException if end dates are after start dates
     */
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

    /**
     * Checks time entered by user is in valid format
     * @param time time of task (deadline/event)
     * @throws BobException if time given by user is not in the format, HHmm
     */
    public static void isValidTime(String time) throws BobException {
        try {
            LocalTime.parse(time, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new BobException(Errors.INVALID_TIME);
        }
    }

}
