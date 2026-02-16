package bob.command;
import bob.exception.BobException;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Represents a command in the Bob chatbot.
 * Each command defines an action the chabot can perform.
 */
public abstract class Command {
    protected boolean isExit = false;

    public abstract String execute(TaskList tasks, Storage storage) throws BobException;

    public boolean isExit() {
        return isExit;
    }
}
