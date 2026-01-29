package bob.task;

import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "data/bob.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task t) {
        tasks.add(t);
        saveTasks();
    }

    public Task delete(int idx) {
        Task removed = tasks.remove(idx - 1);
        saveTasks();
        return removed;
    }

    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }

    public ArrayList<Task> listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        return tasks;
    }

    public void markDone(int idx) {
        tasks.get(idx - 1).markDone();
        saveTasks();
    }

    public void unmark(int idx) {
        tasks.get(idx - 1).unmark();
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
