package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PlainGameOfLifeSimulatorTest {
    private final PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();

    @Test
    public void testDoStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };

        board.setCustomBoard(initialState);

        simulator.doStep(board);

        boolean[][] expectedState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };

        assertArrayEquals(expectedState, board.getBoard());
    }
}
