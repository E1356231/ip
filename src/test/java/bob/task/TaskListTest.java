package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import bob.exception.BobException;

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
    @Test
    public void equals_sameToDoTask_returnsTrue() throws BobException {

        ToDos t1 = new ToDos("Finish Homework");
        ToDos t2 = new ToDos("Finish Homework");
        assertEquals(t1, t2);
    }
    @Test
    public void equals_differentToDoTask_returnsFalse() throws BobException {

        ToDos t1 = new ToDos("Finish Homework");
        ToDos t2 = new ToDos("Buy Groceries");
        assertNotEquals(t1, t2);
    }
    @Test
    public void addDeadlineTest() throws BobException {
        TaskList tasks = new TaskList();

        Deadlines deadline = new Deadlines("Submit Homework", "22/3/26", "2359");
        tasks.add(deadline);

        assertEquals(1, tasks.listTasks().size());
        assertEquals("Submit Homework", tasks.getTask(1).getDescription());
        assertEquals("22/3/26", deadline.getDate());
        assertEquals("2359", deadline.getTime());
        assertFalse(deadline.isDone());
    }
    @Test
    public void equals_sameDeadlineTask_returnsTrue() throws BobException {

        Deadlines d1 = new Deadlines("Submit Homework", "22/3/26", "2359");
        Deadlines d2 = new Deadlines("Submit Homework", "22/3/26", "2359");
        assertEquals(d1, d2);
    }
    @Test
    public void equals_differentDeadlineTask_returnsFalse() throws BobException {

        Deadlines d2 = new Deadlines("Submit Homework", "22/3/26", "2359");
        Deadlines d3 = new Deadlines("Submit Homework", "24/3/26", "2359");
        assertNotEquals(d2, d3);

        Deadlines d4 = new Deadlines("Submit Essay", "22/3/26", "2359");
        assertNotEquals(d4, d2);

        Deadlines d5 = new Deadlines("Submit Homework", "22/3/26", "1700");
        assertNotEquals(d5, d2);
    }
}

