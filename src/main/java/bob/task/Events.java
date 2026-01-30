package bob.task;
import bob.task.Task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 * Represents a Event task in the Bob chatbot.
 * Stores type, description, start date and time, end date and time.
 */
public class Events extends Task {
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
    private final static String TYPE = "E";

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    public Events(String description, String startDate, String startTime, String endDate, String endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");

        LocalDate dateStart = LocalDate.parse(startDate, dateFormat);
        LocalDate dateEnd = LocalDate.parse(endDate, dateFormat);
        LocalTime timeStart = LocalTime.parse(startTime, timeFormat);
        LocalTime timeEnd = LocalTime.parse(endTime, timeFormat);

        this.startDateTime = LocalDateTime.of(dateStart, timeStart);
        this.endDateTime = LocalDateTime.of(dateEnd, timeEnd);

    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yy, h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        if (startDateTime.toLocalDate().equals(endDateTime.toLocalDate())) {
            LocalDate date = startDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();
            return "[E]" + super.toString() + " " + date.format(dateFormatter) + ": "
                    + startTime.format(timeFormatter) + " - "
                    + endTime.format(timeFormatter);
        }
        return "[E]" + super.toString() + " " + this.startDateTime.format(formatter)
                + " to " + this.endDateTime.format(formatter);
    }

    @Override
    public String toFileString() {
        return TYPE + " | " + super.toFileString() + " | " + this.startDate + " - " + this.endDate
                + " | " + this.startTime + " - " + this.endTime;
    }

    public static Events fromFileString(String line) {
        String[] parts = line.split(" \\| ", 5); // T | status | description | dates | times
        String[] dates = parts[3].split(" - ", 2);
        String[] times = parts[4].split(" - ", 2);
        Events e = new Events(parts[2], dates[0], times[0], dates[1], times[1]);
        if (parts[1].equals("1")) e.markDone();
        return e;
    }

}
