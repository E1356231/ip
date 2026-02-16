package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.task.ToDos;

/**
 * Adds a To-do task to in the chatbot.
 */
public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws BobException {
        ToDos todo = new ToDos(description);
        tasks.add(todo);
        storage.saveTasks(tasks.getTasks());
        return "To-Do task added: " + todo;
    }
}
