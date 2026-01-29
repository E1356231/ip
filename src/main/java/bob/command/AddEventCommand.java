package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.task.*;

public class AddEventCommand extends Command {
    private final String description;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public AddEventCommand(String description) {
        this.description = description;
    }

    public void setDateTime(String startDate, String startTime, String endDate, String endTime) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        Events event = new Events(description, startDate, startTime, endDate, endTime);
        tasks.add(event);
        ui.showMessage("Event added: " + event);
        storage.saveTasks(tasks.listTasks());
    }
}
