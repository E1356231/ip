package bob.task;

import bob.task.TaskList;
import bob.task.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addToDoTest() {
        TaskList tasks = new TaskList();

        ToDos toDos = new ToDos("Finish Homework");
        tasks.add(toDos);

        assertEquals(1, tasks.listTasks().size());
        assertEquals("Finish Homework", tasks.getTask(1).getDescription());
        assertFalse(tasks.getTask(1).isDone());
    }
}

