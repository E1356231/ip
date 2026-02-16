package bob.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import bob.exception.BobException;
import bob.task.Deadlines;
import bob.task.Events;
import bob.task.Task;
import bob.task.ToDos;

/**
 * Handles reading and writing to the file for the chatbot.
 * Responsible for saving tasks to disk and loading them at startup.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from file
     * @return
     * @throws BobException
     */
    public ArrayList<Task> load() throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

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
            throw new BobException("Failed to load tasks.");
        }
        return tasks;
    }
    /**
     * Save tasks to file
     * @param tasks
     * @throws BobException
     */
    public void saveTasks(ArrayList<Task> tasks) throws BobException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new BobException("Failed to save tasks.");
        }
    }
}
