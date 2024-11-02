package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeBoardTest {

    private final PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();

    @Test
    void testGameOfLifeBoardGeneration() {
        int n = 100;
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
        board.doSimulationStep(simulator);
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
        board.doSimulationStep(simulator);
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
        board.doSimulationStep(simulator);
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
        board.doSimulationStep(simulator);
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
        board.doSimulationStep(simulator);
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
        board.doSimulationStep(simulator);
        boolean[][] expectedAfterStep = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        assertArrayEquals(expectedAfterStep, board.getBoard());
    }


    @Test
    public void testSetGetBoard() {
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

    @Test
    void testDoSimulationStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);

        board.doSimulationStep(simulator);

        boolean[][] expectedState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        assertArrayEquals(expectedState, board.getBoard());
    }

    @Test
    void  testGetGameOfLifeRow()
    {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);

        boolean[] resultRow = board.getGameOfLifeRow(3);

        boolean[] expectedRow = {true, false, false, true};

        assertArrayEquals(expectedRow, resultRow);
    }

    @Test
    void  testGetGameOfLifeColumn()
    {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);

        boolean[] resultColumn = board.getGameOfLifeColumn(1);

        boolean[] expectedColumn = {false, false, false, false};

        assertArrayEquals(expectedColumn, resultColumn);
    }
    @Test
    void testNeighborsInitialization() {
        GameOfLifeBoard board = new GameOfLifeBoard(3, 3);

        GameOfLifeCell centerCell = board.getCell(1, 1);

        int[][] expectedNeighbors = {{0, 0}, {0, 1}, {0, 2}, {1, 0},{1, 2}, {2, 0}, {2, 1}, {2, 2}};

        assertEquals(expectedNeighbors.length, centerCell.getNeighbors().size());

        for (int[] position : expectedNeighbors) {
            GameOfLifeCell neighborCell = board.getCell(position[0], position[1]);
            assertTrue(centerCell.getNeighbors().contains(neighborCell));
        }
    }
}