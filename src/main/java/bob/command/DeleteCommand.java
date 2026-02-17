package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Deletes a task from the chatbot.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        String deletedTask = tasks.getTask(index).getDescription();
        tasks.delete(index);
        storage.saveTasks(tasks.listTasks());
        return "Deleted: " + deletedTask;
    }
}
