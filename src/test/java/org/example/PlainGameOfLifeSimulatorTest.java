package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);

        simulator.doStep(board);

        boolean[][] expectedState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedState.length * expectedState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedState[i / 4][i % 4]);
            listExpState.add(expC);
        }
        System.out.println(listExpState);
        System.out.println(board.getBoard());
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }
}
