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
    void doStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(3,3);
    }
}