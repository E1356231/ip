package bob;

/**
 * Collection of constant errors in the chatbot
 * Each constant represents the type of error that occurs during user input
 */
public class Errors {
    public static final String INVALID_COMMAND = "Unknown command";
    public static final String INVALID_DATE = "Invalid date format try d/M/yy";
    public static final String INVALID_TIME = "Invalid time format try HHmm";
    public static final String MISSING_NUMBER = "No task number given";
    public static final String MISSING_KEYWORD = "Provide a keyword";
    public static final String DUPLICATE = "Duplicate task.\n";
}
