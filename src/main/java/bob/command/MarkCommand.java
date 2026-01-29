package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        tasks.markDone(index);
        ui.showMessage("Task marked done: " + tasks.getTask(index));
        storage.saveTasks(tasks.listTasks());
    }
}
