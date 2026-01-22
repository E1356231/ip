import java.util.ArrayList;
import java.util.Collections;

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void delete(int idx) {
        Task removed = tasks.remove(idx - 1);
        System.out.println("Task removed: " + removed);
    }

    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }
}
