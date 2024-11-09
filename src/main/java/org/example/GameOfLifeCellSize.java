package org.example;

import java.util.List;
import java.util.Objects;

public abstract class GameOfLifeCellSize<T extends GameOfLifeCell> {
    protected List<T> cells;

    public GameOfLifeCellSize(List<T> cells) {
        this.cells = List.copyOf(Objects.requireNonNull(cells));
    }

    public int countAliveCells() {
        int count = 0;
        for (T cell : cells) {
            if (cell.getCellValue()) {
                count++;
            }
        }
        return count;
    }

    public int countDeadCells() {
        return cells.size() - countAliveCells();
    }
}
