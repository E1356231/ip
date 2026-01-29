package bob.command;
import bob.exception.BobException;
import bob.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BobException;

    public boolean isExit() {
        return isExit;
    }
}
