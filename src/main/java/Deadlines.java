public class Deadlines extends Task{
    private String date;
    private String time;

    public Deadlines(String description, String date, String time) {
        this.date = date;
        this.time = time;
        super(description);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.date + " " + this.time;
    }
}
