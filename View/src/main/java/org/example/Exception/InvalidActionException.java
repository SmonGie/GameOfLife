package org.example.Exception;

public class InvalidActionException extends GameOfLifeBoardException {
    public InvalidActionException(String message) {
        super(message);
    }

    public InvalidActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
