package bob.task;
/**
 * Represents a single task in the Bob chatbot.
 * Stores a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Mark the task as done
    public void markDone() {
        this.isDone = true;
    }

    // Unmark the task
    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }


    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    // Write task to file
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    // Read task from file
    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ",2);
        Task task = new Task(parts[1]);
        if (parts[0].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
