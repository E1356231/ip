package bob.command;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Task(s) in your list:");
        tasks.listTasks();
    }
}
