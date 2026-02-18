package bob.parser;

import bob.DateUtil;
import bob.Errors;
import bob.command.AddDeadlineCommand;
import bob.command.AddEventCommand;
import bob.command.AddToDoCommand;
import bob.command.ClearAllCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.exception.BobException;
/**
 * Parses user input for the chatbot.
 * Converts raw input strings into corresponding Command objects.
 */
public class Parser {
    /**
     * @param fullCommand command given by user
     * @return respective actions based on the user's inputs e.g. delete tasks, mark/unmark tasks, add tasks
     * @throws BobException if:
     *      <ul>
     *          <li>The command keyword is not recognized</li>
     *          <li>A required description, date, or time is missing</li>
     *          <li>A task number or keyword is missing for commands like mark, unmark, delete, or find</li>
     *      </ul>
     */


    public static Command parse(String fullCommand) throws BobException {
        String[] parts = fullCommand.trim().split("\\s+"); // split by space
        String word = parts[0].toLowerCase();

        switch (word) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "reset":
            return new ClearAllCommand();
        case "todo":
            assert parts.length >= 2 : "To-Do task must have a description";
            if (parts.length < 2) {
                throw new BobException("missing description");
            }
            StringBuilder descriptionTodo = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
                descriptionTodo.append(parts[i]).append(" ");
            }
            String todo = descriptionTodo.toString().trim();
            return new AddToDoCommand(todo);
        case "deadline":
            assert parts.length >= 4 : "Deadline task must have a description, due date and time";
            if (parts.length < 4) {
                throw new BobException("missing either description/date/time");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < parts.length - 2; i++) {
                sb.append(parts[i]).append(" ");
            }
            String description = sb.toString().trim();
            String date = parts[parts.length - 2];
            String time = parts[parts.length - 1];

            DateUtil.isValidDate(date);
            DateUtil.isValidTime(time);

            return new AddDeadlineCommand(description, date, time);
        case "event":
            assert parts.length >= 6 : "Event must have a description, start date and time, end date and time";
            if (parts.length < 6) {
                throw new BobException("missing either description/start datetime/end datetime");
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


            DateUtil.isValidEndDate(startDate, endDate);
            DateUtil.isValidTime(startTime);
            DateUtil.isValidTime(endTime);

            return new AddEventCommand(event, startDate, startTime, endDate, endTime);
        case "mark":
            if (parts.length < 2) {
                throw new BobException(Errors.MISSING_NUMBER);
            }
            return new MarkCommand(Integer.parseInt(parts[1]));
        case "unmark":
            if (parts.length < 2) {
                throw new BobException(Errors.MISSING_NUMBER);
            }
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        case "delete":
            if (parts.length < 2) {
                throw new BobException(Errors.MISSING_NUMBER);
            }
            return new DeleteCommand(Integer.parseInt(parts[1]));
        case "find":
            if (parts.length < 2) {
                throw new BobException(Errors.MISSING_KEYWORD);
            }
            return new FindCommand(parts[1]);
        default:
            throw new BobException(Errors.INVALID_COMMAND);
        }
    }
}
