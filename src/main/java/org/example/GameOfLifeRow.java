package org.example;

import java.util.List;

public class GameOfLifeRow extends GameOfLifeCellSize implements CellObserver {
    public GameOfLifeRow(List<GameOfLifeCell> cells) {
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
