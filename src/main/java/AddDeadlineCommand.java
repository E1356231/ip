public class AddDeadlineCommand extends Command {
    private final String description;
    private String date;
    private String time;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }
    public void setDateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Deadlines deadline = new Deadlines(description, date, time);
        tasks.add(deadline);
        ui.showMessage("Deadline task added: " + deadline);
        storage.saveTasks(tasks.listTasks());
    }
}
