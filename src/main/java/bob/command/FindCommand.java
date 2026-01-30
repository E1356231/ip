package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

import java.util.ArrayList;
/**
 * Finds tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = tasks.find(keyword);
        ui.showFoundTasks(results);
    }
}
