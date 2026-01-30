package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.task.ToDos;
import bob.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    @Test
    public void markTaskTest() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("Finish Homewrok"));
        Ui ui = new Ui();
        Storage storage = new Storage("data/text.txt");

        assertFalse(tasks.getTask(1).isDone());

        MarkCommand markCommand = new MarkCommand(1);
        markCommand.execute(tasks, ui, storage);

        assertTrue(tasks.getTask(1).isDone());
    }
}
