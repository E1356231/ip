package bob.parser;

import bob.command.*;
import bob.exception.BobException;
/**
 * Parses user input for the chatbot.
 * Converts raw input strings into corresponding Command objects.
 */
public class Parser {
    /**
     * @param fullCommand
     * @return
     * @throws BobException
     */
    public static Command parse(String fullCommand) throws BobException {
        String[] parts = fullCommand.trim().split("\\s+"); // split by space
        String word = parts[0].toLowerCase();

        switch (word) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (parts.length < 2) {
                throw new BobException("ERROR! Description Missing");
            }
            StringBuilder descriptionTodo = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                descriptionTodo.append(parts[i]).append(" ");
            }
            String todo = descriptionTodo.toString().trim();
            return new AddToDoCommand(todo);
        case "deadline":
            if (parts.length < 4) {
                throw new BobException("ERROR! Description/Date/Time Missing");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < parts.length - 2; i++) {
                sb.append(parts[i]).append(" ");
            }
            String description = sb.toString().trim();
            String date = parts[parts.length - 2];
            String time = parts[parts.length - 1];
            return new AddDeadlineCommand(description, date, time);
        case "event":
            if (parts.length < 6) {
                throw new BobException("ERROR! Description/Start Date/Start Time/End Date/End Time Missing");
            }
            StringBuilder stringbuilder = new StringBuilder();
            for (int i = 1; i < parts.length - 4; i++) {
                stringbuilder.append(parts[i]).append(" ");
            }
            String event = stringbuilder.toString().trim();
            String startDate = parts[parts.length - 4];
            String startTime = parts[parts.length - 3];
            String endDate = parts[parts.length - 2];
            String endTime = parts[parts.length - 1];
            return new AddEventCommand(event, startDate, startTime, endDate, endTime);
        case "mark":
            if (parts.length < 2) {
                throw new BobException("You've not specified a task number");
            }
            return new MarkCommand(Integer.parseInt(parts[1]));
        case "unmark":
            if (parts.length < 2) {
                throw new BobException("Specify task number to unmark!");
            }
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        case "delete":
            if (parts.length < 2) {
                throw new BobException("Specify task number to delete!");
            }
            return new DeleteCommand(Integer.parseInt(parts[1]));
        case "find":
            if (parts.length < 2) {
                throw new BobException("Please provide a keyword");
            }
            return new FindCommand(parts[1]);
        default:
            throw new BobException("Unknown command: " + word);
        }
    }
}
