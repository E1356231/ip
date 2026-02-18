package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bob.storage.Storage;
import bob.task.Deadlines;
import bob.task.TaskList;
import bob.task.ToDos;

public class CommandTest {
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
    @Test
    public void unmarkTaskTest() throws Exception {
        TaskList tasks = new TaskList();
        Deadlines deadline = new Deadlines("Submit Homework", "23/2/26", "2359");
        tasks.add(deadline);
        tasks.markDone(1);
        Storage storage = new Storage("data/text.txt");

        assertTrue(tasks.getTask(1).isDone());

        UnmarkCommand unmark = new UnmarkCommand(1);
        unmark.execute(tasks, storage);

        assertFalse(tasks.getTask(1).isDone());
    }
    @Test
    public void deleteTaskTest() throws Exception {
        TaskList tasks = new TaskList();
        Deadlines deadline = new Deadlines("Submit Homework", "23/2/26", "2359");
        tasks.add(deadline);
        Storage storage = new Storage("data/text.txt");

        assertEquals(1, tasks.getSize());

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(tasks, storage);

        assertEquals(0, tasks.getSize());
    }
    @Test
    public void clearAllTaskTest() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new ToDos("Finish Homework"));
        tasks.add(new Deadlines("Submit Homework", "23/2/26", "2359"));
        Storage storage = new Storage("data/text.txt");

        assertEquals(2, tasks.getSize());

        ClearAllCommand clear = new ClearAllCommand();
        clear.execute(tasks, storage);

        assertEquals(0, tasks.getSize());
    }
}
