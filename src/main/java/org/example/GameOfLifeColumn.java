package org.example;

import java.util.List;

public class GameOfLifeColumn extends GameOfLifeCellSize implements CellObserver {
    public GameOfLifeColumn(List<GameOfLifeCell> cells) {
        super(cells);
        for (GameOfLifeCell cell : cells) {
            cell.addObserver(this);
        }
    }

    @Override
    public void update() {
        System.out.println("Kolumna zaktualizowana: Żywe komórki = " + countAliveCells() + ", Martwe komórki = "
                + countDeadCells());
    }
}
