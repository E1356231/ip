package bob.command;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Exits the chatbot.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye!";
    }
}
