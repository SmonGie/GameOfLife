package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

        board.setCustomBoard(initialState);
        System.out.println(Arrays.deepToString(board.getBoard()));

        board.doStep();
        System.out.println(Arrays.deepToString(board.getBoard()));

        boolean[][] expectedState = {
                {false,false,false},
                {false,false,false},
                {false,false,false}
        };
        assertArrayEquals(expectedState, board.getBoard());

    }
}