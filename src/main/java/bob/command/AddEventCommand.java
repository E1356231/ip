package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Events;
import bob.task.TaskList;

/**
 * Adds an Event task to in the chatbot.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;

    /**
     * @param description title of event task
     * @param startDate start date event
     * @param startTime start time of event
     * @param endDate end date of event
     * @param endTime end time of event
     */
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
