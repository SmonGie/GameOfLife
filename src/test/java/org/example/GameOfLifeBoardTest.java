package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeBoardTest {
    @Test
    void testGameOfLifeBoardGeneration() {
        int n = 10000;
        GameOfLifeBoard board1 = new GameOfLifeBoard(n, n);
        GameOfLifeBoard board2 = new GameOfLifeBoard(n, n);

        assertNotEquals(board1, board2);
    }

    @Test
    void testDoStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(3, 3);
        boolean[][] initialState = {
                {true, false, true},
                {false, true, false},
                {true, true, false}
        };

        board.setCustomBoard(initialState);
        //System.out.println(Arrays.deepToString(board.getBoard()));
        board.doStep(board);
        //System.out.println(Arrays.deepToString(board.getBoard()));
        boolean[][] expectedState = {
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };
        assertArrayEquals(expectedState, board.getBoard());
    }

    @Test
    public void test3and4neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        board.setCustomBoard(initialState);
        board.doStep(board);
        boolean[][] expectedAfterStep = {
                {false, false, false, false},
                {true, true, true, false},
                {true, false, true, false},
                {true, true, true, false}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }

    @Test
    public void test2neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, true, true, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };
        board.setCustomBoard(initialState);
        board.doStep(board);
        boolean[][] expectedAfterStep = {
                {true, true, true, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }

    @Test
    public void test1neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, true, false},
                {false, false, false, false}
        };
        board.setCustomBoard(initialState);
        board.doStep(board);
        boolean[][] expectedAfterStep = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }

    @Test
    public void test0neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, false, false},
                {false, false, false, false}
        };
        board.setCustomBoard(initialState);
        board.doStep(board);
        boolean[][] expectedAfterStep = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }

    @Test
    public void testEdgeConditions() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);
        board.doStep(board);
        boolean[][] expectedAfterStep = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }


    @Test
    public void testGetBoard() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);
        assertArrayEquals(initialState, board.getBoard());
    }

    @Test
    public void testSimulateSingleGeneration() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        board.setCustomBoard(initialState);
        board.simulate(1);
        boolean[][] expactedAfterSimulation = {
                {false, false, false, false},
                {true, true, true, false},
                {true, false, true, false},
                {true, true, true, false}
        };
        assertArrayEquals(expactedAfterSimulation, board.getBoard());
    }

    @Test
    public void testSimulateTwoGenerations() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        board.setCustomBoard(initialState);
        board.simulate(2);
        boolean[][] expactedAfterSimulation = {
                {false, false, false, false},
                {true, false, true, false},
                {false, false, false, false},
                {true, false, true, false}
        };
        assertArrayEquals(expactedAfterSimulation, board.getBoard());
    }

    @Test
    public void testDisplay() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        board.setCustomBoard(initialState);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        board.showBoard();
        System.setOut(originalOut);
        String output = outputStream.toString();
        String expectedOutput = ". . . . " + System.lineSeparator()
                + ". O . . " + System.lineSeparator()
                + "O O O . " + System.lineSeparator()
                + ". O . . " + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

}