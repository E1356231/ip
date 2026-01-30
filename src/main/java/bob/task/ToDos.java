package bob.task;
import bob.task.Task;

/**
 * Represents a To-Do task in the Bob chatbot.
 * Stores type and description only.
 */
public class ToDos extends Task {
    public static final String TYPE = "T";
    public ToDos(String description){
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return TYPE + " | " + super.toFileString();
    }

    public static ToDos fromFileString(String line) {
        String[] parts = line.split(" \\| ", 3); // T | status | description
        ToDos t = new ToDos(parts[2]);
        if (parts[1].equals("1")) t.markDone();
        return t;
    }
}
