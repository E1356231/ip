package bob;

import bob.command.Command;
import bob.exception.BobException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskList;

/**
 * The main class for Bob chatbot.
 * Handles the initialization of the task list, user interface,
 * and execution of commands.
 */
public class Bob {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Main chatbot class that coordinates user interaction
     */
    public Bob() {
        storage = new Storage("data/bob.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            tasks = new TaskList();
        }
    }
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, storage);

            if (command.isExit()) {
                return response + "EXIT_SIGNAL";
            }
            return response;
        } catch (BobException e) {
            return "ERROR! " + e.getMessage();
        }
    }
}

