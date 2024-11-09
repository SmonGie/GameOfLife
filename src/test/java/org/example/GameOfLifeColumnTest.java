package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeColumnTest {
    @Test
    void testCountAliveCellsInColumn() {
        List<GameOfLifeCell> cells = new ArrayList<>(Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        ));

        GameOfLifeColumn column = new GameOfLifeColumn(cells);
        assertEquals(2, column.countAliveCells());
    }

    @Test
    void testCountDeadCellsInRow() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeColumn column = new GameOfLifeColumn(cells);
        assertEquals(2, column.countDeadCells());
    }
}