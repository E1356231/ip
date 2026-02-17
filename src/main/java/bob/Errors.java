package bob;

/**
 * Collection of constant errors in the chatbot
 * Each constant represents the type of error that occurs during user input
 */
public class Errors {
    public static final String INVALID_COMMAND = "unknown command";
    public static final String INVALID_DATE = "invalid date format try d/M/yy";
    public static final String INVALID_TIME = "invalid time format try HHmm";
    public static final String MISSING_NUMBER = "no task number given";
    public static final String MISSING_KEYWORD = "provide a keyword";
    public static final String DUPLICATE = "task already exists\n";
}
