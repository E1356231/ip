public class TaskList {
    private Task[] tasks = new Task[100];
    private int size = 0;

    public void add(Task t) {
        tasks[size] = t;
        size++;
    }

    public Task getTask(int idx) {
        return tasks[idx - 1];
    }

    public void listTasks() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }
}
