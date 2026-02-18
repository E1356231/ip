package bob.command;
import bob.storage.Storage;
import bob.task.TaskList;
/**
 * Lists out all task in the chatbot.
 */
public class ListCommand extends Command {
    @Override
        public String execute(TaskList tasks, Storage storage) {
        if (tasks.getSize() > 0) {
            return ("Task(s) in your list:\n" + tasks.getTaskList());
        }
        return ("You have no tasks.");
    }
}
