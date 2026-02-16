package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Unmarks a task from the list in the chatbot.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        tasks.unmark(index);
        storage.saveTasks(tasks.listTasks());
        return "Task unmarked: " + tasks.getTask(index);
    }
}
