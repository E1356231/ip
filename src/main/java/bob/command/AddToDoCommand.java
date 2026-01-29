package bob.command;
import bob.exception.BobException;
import bob.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;
import bob.task.ToDos;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobException {
        ToDos todo = new ToDos(description);
        tasks.add(todo);
        ui.showMessage("To-Do task added: " + todo);
        storage.saveTasks(tasks.listTasks());
    }
}
