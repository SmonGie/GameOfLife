package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeRowTest {
    @Test
    void testCountAliveCellsInRow() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeRow row = new GameOfLifeRow(cells);
        assertEquals(2, row.countAliveCells());
    }

    @Test
    void testCountDeadCellsInRow() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeRow row = new GameOfLifeRow(cells);
        assertEquals(2, row.countDeadCells());
    }
}