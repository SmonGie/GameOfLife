package org.example;

import java.util.List;

public class GameOfLifeColumn<T extends GameOfLifeCell> extends GameOfLifeCellSize<T> implements CellObserver {
    public GameOfLifeColumn(List<T> cells) {
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
