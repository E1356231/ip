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
    private final String date;
    private final String time;

    /**
     * @param description title of deadline task
     * @param date due date of deadline task
     * @param time time due of deadline task
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
