package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Marks a task as complete from list in the chatbot.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        tasks.markDone(index);
        storage.saveTasks(tasks.listTasks());
        return "Task marked done: " + tasks.getTask(index);
    }
}
