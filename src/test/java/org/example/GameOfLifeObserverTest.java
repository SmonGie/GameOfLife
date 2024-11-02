package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeObserverTest {

    private GameOfLifeBoard board;
    private GameOfLifeRow row;
    private GameOfLifeColumn column;

    @BeforeEach
    public void setUp() {
        board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        board.setCustomBoard(initialState);

        List<GameOfLifeCell> rowCells = new ArrayList<>();
        List<GameOfLifeCell> colCells = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            rowCells.add(board.getCell(1, i));
            colCells.add(board.getCell(i, 1));
        }

        row = new GameOfLifeRow(rowCells);
        column = new GameOfLifeColumn(colCells);
    }

    @Test
    public void testObserverNotificationStateChange() {

        int initialAliveCountRow = row.countAliveCells();
        int initialAliveCountColumn = column.countAliveCells();

        GameOfLifeCell centerCell = board.getCell(1, 1);
        centerCell.updateState(true);
        board.getCell(1,2).updateState(true);
        int newAliveCountRow = row.countAliveCells();
        int newAliveCountColumn = column.countAliveCells();

        assertEquals(initialAliveCountRow + 2, newAliveCountRow);
        assertEquals(initialAliveCountColumn + 1, newAliveCountColumn);
    }

}