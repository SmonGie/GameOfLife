package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeBoardTest {
    @Test
    void testGameOfLifeBoard() {
        int n = 5;
        GameOfLifeBoard board1 = new GameOfLifeBoard(n,n);
        GameOfLifeBoard board2 = new GameOfLifeBoard(n,n);

        assertNotEquals(board1, board2);

    }

    @Test
    void testDoStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(3,3);
        boolean[][] initialState = {
                {true, false, true},
                {false, true, false},
                {true, true, false}
        };

        board = setCustomBoard(board, initialState);

        board.doStep();

        boolean[][] expectedState = {
                {false,false,false},
                {false,false,false},
                {false,false,false}
        };
        assertArrayEquals(expectedState, board.getBoard());

    }
    private GameOfLifeBoard setCustomBoard(GameOfLifeBoard board, boolean[][] customBoard) {
        boolean[][] currentBoard = board.getBoard();
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[0].length; j++) {
                currentBoard[i][j] = customBoard[i][j];
            }
        }
        return board;
    }
}