package bob.command;

import java.util.ArrayList;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
/**
 * Finds tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> results = tasks.find(keyword);
        if (results.isEmpty()) {
            return "No matching tasks found";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < results.size(); i++) {
            sb.append((i + 1) + ". " + results.get(i) + "\n");
        }
        return sb.toString();
    }
}
