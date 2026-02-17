package bob.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bob chatbot.
 * Responsible for adding, deleting, marking, unmarking
 * and saving, loading tasks from the file
 */

public class TaskList {
    private static final String FILE_PATH = "data/bob.txt";
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to list
      */
    public void add(Task t) {
        tasks.add(t);
        saveTasks();
    }
    /**
     * Remove task from list
     * @param idx
     * @return
     */
    public Task delete(int idx) {
        Task removed = tasks.remove(idx - 1);
        saveTasks();
        return removed;
    }
    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }
    public int getSize() {
        return tasks.size();
    }
    /**
     * prints the list of tasks
     * @return list of tasks in the list
     */
    public ArrayList<Task> listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        return tasks;
    }

    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Mark task as done
     * @param idx number that indicates the task in the list
     */
    public void markDone(int idx) {
        tasks.get(idx - 1).markDone();
        saveTasks();
    }
    /**
     * unmark task
     * @param idx
     */
    public void unmark(int idx) {
        tasks.get(idx - 1).unmark();
        saveTasks();
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
    /**
     * find tasks in the list matching the word given
     * @param word input keyword to find matching tasks
     * @return tasks that match the given word
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase()
                    .contains(word.toLowerCase())) {
                result.add(task);
            }
        }
        return result;
    }
}
