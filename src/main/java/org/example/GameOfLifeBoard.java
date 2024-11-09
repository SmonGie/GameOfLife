package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameOfLifeBoard {
    private List<GameOfLifeCell> board;
    int width;
    int height;

    public GameOfLifeBoard(int width, int height) {
        this.width = width;
        this.height = height;

        GameOfLifeCell[] cells = new GameOfLifeCell[width * height];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new GameOfLifeCell();
        }
        board = List.of(cells);
        initializeNeighbors();
    }

    private void initializeNeighbors() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int neighborRow = (i + k + width) % width;
                        int neighborCol = (j + l + height) % height;
                        board.get(i * height + j).addNeighbor(board.get(neighborRow * height + neighborCol));
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void showBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(board.get(i * height + j).getCellValue() ? "O " : ". ");
            }
            System.out.println();
        }
    }

    public List<GameOfLifeCell> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public void setCustomBoard(List<GameOfLifeCell> customBoard) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.get(i * height + j).updateState(customBoard.get(i * height + j).getCellValue());
            }
        }
    }

    public void doSimulationStep(GameOfLifeSimulator<GameOfLifeBoard> simulator) {
        simulator.doStep(this);
    }

    public List<GameOfLifeCell> getGameOfLifeRow(int rowIndex) {
        return Collections.unmodifiableList(board.subList(rowIndex * width, (rowIndex + 1) * width));
    }

    public GameOfLifeCell getCell(int row, int col) {
        return board.get(row * width + col);
    }


    public List<GameOfLifeCell> getGameOfLifeColumn(int columnIndex) {
        List<GameOfLifeCell> column = Arrays.asList(new GameOfLifeCell[height]);
        for (int i = 0; i < height; i++) {
            column.set(i, board.get(i * height + columnIndex));
        }
        return Collections.unmodifiableList(column);
    }
}
