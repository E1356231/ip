public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ",2);
        Task task = new Task(parts[1]);
        if (parts[0].equals("1")) {
            task.markDone();
        }
        return task;
    }
}
