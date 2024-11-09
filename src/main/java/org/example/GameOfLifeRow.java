package org.example;

import java.util.List;

public class GameOfLifeRow<T extends GameOfLifeCell> extends GameOfLifeCellSize<T> implements CellObserver {
    public GameOfLifeRow(List<T> cells) {
        super(cells);
        for (GameOfLifeCell cell : cells) {
            cell.addObserver(this);
        }
    }

    @Override
    public void update() {
        System.out.println("Wiersz zaktualizowany: Żywe komórki = " + countAliveCells() + ", Martwe komórki = "
                + countDeadCells());
    }
}
