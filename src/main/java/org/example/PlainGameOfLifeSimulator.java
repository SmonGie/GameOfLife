package org.example;

import java.util.ArrayList;
import java.util.List;

public class PlainGameOfLifeSimulator implements GameOfLifeSimulator<GameOfLifeBoard> {
    @Override
    public void doStep(GameOfLifeBoard golb) {
        boolean[][] tempGolCells = new boolean[golb.getWidth()][golb.getHeight()];
        for (int i = 0; i < golb.getWidth(); i++) {
            for (int j = 0; j < golb.getHeight(); j++) {
                tempGolCells[i][j] = golb.getCell(i, j).nextState();
            }
        }
        for (int i = 0; i < golb.getWidth(); i++) {
            for (int j = 0; j < golb.getHeight(); j++) {
                golb.getCell(i, j).updateState(tempGolCells[i][j]);
            }
        }
    }
}
