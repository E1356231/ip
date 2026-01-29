import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private final String date;
    private final String time;
    private final LocalDate dateDue;
    private final LocalTime timeDue;
    private static final String TYPE = "D";

    public Deadlines(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");

        this.dateDue = LocalDate.parse(date, dateFormat);
        this.timeDue = LocalTime.parse(time, timeFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        return "[D]" + super.toString() + " by " + this.dateDue.format(dateFormatter) + ", "
                + this.timeDue.format(timeFormatter);
    }

    @Override
    public String toFileString() {
        return TYPE + " | " + super.toFileString() + " | " + this.date + ", " + this.time;
    }

    public static Deadlines fromFileString(String line) {
        String[] parts = line.split(" \\| ", 4); // T | status | description | date and time
        String[] dateTime = parts[3].split(", ", 2);
        Deadlines d = new Deadlines(parts[2], dateTime[0], dateTime[1]);
        if (parts[1].equals("1")) d.markDone();
        return d;
    }
}
