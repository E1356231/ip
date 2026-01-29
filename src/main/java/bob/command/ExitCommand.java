package bob.command;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
    }
}
