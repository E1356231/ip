package bob.task;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bob chatbot.
 * Responsible for adding, deleting, marking, unmarking
 * and saving, loading tasks from the file
 */

public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "data/bob.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    // Add task to list
    public void add(Task t) {
        tasks.add(t);
        saveTasks();
    }
    // Remove task from list
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

    // Mark task as done
    public void markDone(int idx) {
        tasks.get(idx - 1).markDone();
        saveTasks();
    }

    // Unmark task
    public void unmark(int idx) {
        tasks.get(idx - 1).unmark();
        saveTasks();
    }

    // Load tasks from file
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
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }

    // Save tasks to file
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
