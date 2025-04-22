package main.exception;

/**
 * Custom exception to handle database-related errors.
 */
public class DatabaseException extends Exception {

    // Constructor with error message
    public DatabaseException(String message) {
        super(message!=null ? message : "Unknown Database error has occured");
    }

    // Constructor with message and root cause
    public DatabaseException(String message, Throwable cause) {
        super(message!=null ? message : "Unknown Database error has occured", cause);
    }
}
