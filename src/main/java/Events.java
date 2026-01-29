public class Events extends Task{
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private final static String TYPE = "E";

    public Events(String description, String startDate, String startTime, String endDate, String endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.startDate + " to " + this.endDate + ": "
                + this.startTime + " - " + this.endTime;
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
