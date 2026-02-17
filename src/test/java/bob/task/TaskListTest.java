package bob.task;

import bob.exception.BobException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {

    @Test
    public void addToDoTest() throws BobException {
        TaskList tasks = new TaskList();

        ToDos toDos = new ToDos("Finish Homework");
        tasks.add(toDos);

        assertEquals(1, tasks.listTasks().size());
        assertEquals("Finish Homework", tasks.getTask(1).getDescription());
        assertFalse(tasks.getTask(1).isDone());
    }
}

