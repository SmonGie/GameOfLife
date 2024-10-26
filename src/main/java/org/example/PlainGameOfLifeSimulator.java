package org.example;

public class PlainGameOfLifeSimulator implements GameOfLifeSimulator {
    @Override
    public void doStep(GameOfLifeBoard golb) {
        boolean[][] newBoard = new boolean[golb.getBoard().length][golb.getBoard()[0].length];
        for (int i = 0; i < golb.getBoard().length; i++) {
            for (int j = 0; j < golb.getBoard()[i].length; j++) {
                int liveNeighbors = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int neighbourRow = (i + k + golb.getBoard().length) % golb.getBoard().length;
                        int neighbourCol = (j + l + golb.getBoard()[0].length) % golb.getBoard()[0].length;
                        if (golb.getBoard()[neighbourRow][neighbourCol]) {
                            liveNeighbors++;
                        }
                    }
                }
                newBoard[i][j] = (liveNeighbors == 3 || (golb.getBoard()[i][j] && liveNeighbors == 2));
            }
        }
        golb.setCustomBoard(newBoard);
    }
}
