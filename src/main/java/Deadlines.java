public class Deadlines extends Task{
    private String date;
    private String time;
    private static final String TYPE = "D";

    public Deadlines(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.date + " " + this.time;
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
