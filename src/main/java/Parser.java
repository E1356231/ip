public class Parser {
    public static Command parse(String fullCommand) throws BobException {
        String[] parts = fullCommand.split(" ", 2); // split by space
        String word = parts[0].toLowerCase();

        switch (word) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (parts.length < 2) throw new BobException("Specify task number to mark done!");
                return new MarkCommand(Integer.parseInt(parts[1]));
            case "unmark":
                if (parts.length < 2) throw new BobException("Specify task number to unmark!");
                return new UnmarkCommand(Integer.parseInt(parts[1]));
            case "delete":
                if (parts.length < 2) throw new BobException("Specify task number to delete!");
                return new DeleteCommand(Integer.parseInt(parts[1]));
            case "todo":
                if (parts.length < 2) throw new BobException("Specify description for todo!");
                return new AddToDoCommand(parts[1]);
            case "deadline":
                if (parts.length < 2) throw new BobException("Specify description and date/time!");
                return new AddDeadlineCommand(parts[1]);
            case "event":
                if (parts.length < 2) throw new BobException("Specify description and date/time!");
                return new AddEventCommand(parts[1]);
            default:
                throw new BobException("Unknown command: " + word);
        }
    }
}
