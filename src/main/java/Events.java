public class Events extends Task{
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public Events(String description, String startDate, String startTime, String endDate, String endTime) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.startDate + " to " + this.endDate + ": "
                + this.startTime + " - " + this.endTime;
    }

}
