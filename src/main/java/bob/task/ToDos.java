package bob.task;

/**
 * Represents a To-Do task in the Bob chatbot.
 * Stores type and description only.
 */
public class ToDos extends Task {
    public static final String TYPE = "T";

    public ToDos(String description) {
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

    /**
     * Creates an Events object from a line in the save file.
     * @param line the line read from the save file
     * @return a new Events object corresponding to the data in the line
     */
    public static ToDos fromFileString(String line) {
        String[] parts = line.split(" \\| ", 3); // T | status | description
        ToDos t = new ToDos(parts[2]);
        if (parts[1].equals("1")) {
            t.markDone();
        }
        return t;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
