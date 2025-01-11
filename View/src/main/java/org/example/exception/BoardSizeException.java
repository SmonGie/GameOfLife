package org.example.exception;

public class BoardSizeException extends GameOfLifeBoardException {
    public BoardSizeException(String message) {
        super(message);
    }

    public BoardSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
