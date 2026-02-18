package bob.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.task.ToDos;

public class AddCommandTest {
    @Test
    public void markTaskTest() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("Finish Homework"));
        Storage storage = new Storage("data/text.txt");

        assertFalse(tasks.getTask(1).isDone());

        MarkCommand markCommand = new MarkCommand(1);
        markCommand.execute(tasks, storage);

        assertTrue(tasks.getTask(1).isDone());
    }
}
