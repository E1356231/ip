import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class TaskList {
    private ArrayList<Task> tasks;
    private  static final String FILE_PATH = "data/bob.txt";

    public TaskList() {
        this.tasks = loadTasks();
    }

    public void add(Task t) {
        tasks.add(t);
        saveTasks();
    }

    public void delete(int idx) {
        Task removed = tasks.remove(idx - 1);
        saveTasks();
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

    public void markDone(int idx) {
        tasks.get(idx - 1).markDone();
        saveTasks();
    }

    public void unmark(int idx) {
        tasks.get(idx - 1).unmark();
        saveTasks();
    }

    public void clearList() {
        tasks.clear();
        saveTasks();
    }

    private ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Task task = null;
                char type = line.charAt(0);

                switch (type) {
                    case 'T':
                        task = ToDos.fromFileString(line);
                        break;
                    case 'D':
                        task = Deadlines.fromFileString(line);
                        break;
                    case 'E':
                        task = Events.fromFileString(line);
                        break;
                    default:
                        continue;
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }

    private void saveTasks() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
