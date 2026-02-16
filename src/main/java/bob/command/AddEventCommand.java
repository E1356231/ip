package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.*;
/**
 * Adds a Event task to in the chatbot.
 */
public class AddEventCommand extends Command {
    private final String description;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public AddEventCommand(String description, String startDate, String startTime,
                           String endDate, String endTime) {
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        Events event = new Events(description, startDate, startTime, endDate, endTime);
        tasks.add(event);
        storage.saveTasks(tasks.getTasks());
        return "Event added: " + event;
    }
}
