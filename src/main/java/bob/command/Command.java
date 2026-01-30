package bob.command;
import bob.exception.BobException;
import bob.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;
/**
 * Represents a command in the Bob chatbot.
 * Each command defines an action the chabot can perform.
 */
public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobException;

    public boolean isExit() {
        return isExit;
    }
}
