package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.Deadlines;
import bob.task.TaskList;
/**
 * Adds a Deadline task to in the chatbot.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private String date;
    private String time;

    /**
     * @param description
     * @param date
     * @param time
     */
    public AddDeadlineCommand(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        Deadlines deadline = new Deadlines(description, date, time);
        tasks.add(deadline);
        storage.saveTasks(tasks.getTasks());
        return "Deadline task added: " + deadline;
    }
}
