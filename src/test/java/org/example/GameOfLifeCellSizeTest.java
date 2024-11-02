package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeCellSizeTest {

    @Test
    void countAliveCells() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize = new GameOfLifeCellSize<>(cells) {};
        assertEquals(2, cellSize.countAliveCells());
    }

    @Test
    void countDeadCells() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize = new GameOfLifeCellSize<>(cells) {};
        assertEquals(3, cellSize.countDeadCells());
    }
}