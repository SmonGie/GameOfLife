package org.example.exception;

public class GameOfLifeBoardException extends Exception {
    public GameOfLifeBoardException(String message) {
        super(message);
    }

    public GameOfLifeBoardException(String message, Throwable cause) {
        super(message, cause);
    }

}

