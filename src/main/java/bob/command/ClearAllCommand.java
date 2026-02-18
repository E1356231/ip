package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;

/**
 * Resets tasklist of the chatbot
 */
public class ClearAllCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.deleteAll();
        return "Deleted all tasks.";
    }
}
