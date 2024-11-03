package org.example;


import java.util.ArrayList;
import java.util.List;

public class GameOfLifeBoard {
    private List<GameOfLifeCell> board = new ArrayList<GameOfLifeCell>();
    int width;
    int height;

    public GameOfLifeBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new ArrayList<>(width * height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board.add(i * height + j, new GameOfLifeCell());
            }
        }
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
        List<GameOfLifeCell> newBoard = new ArrayList<GameOfLifeCell>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newBoard.add(i * height + j, board.get(i * height + j));
            }
        }
        return newBoard;
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
        List<GameOfLifeCell> row = new ArrayList<>();
        for (int j = 0; j < width; j++) {
            row.add(j, board.get(rowIndex * width + j));
        }
        return row;
    }

    public GameOfLifeCell getCell(int row, int col) {
        return board.get(row * width + col);
    }


    public List<GameOfLifeCell> getGameOfLifeColumn(int columnIndex) {
        List<GameOfLifeCell> column = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            column.add(i, board.get(i * height + columnIndex));
        }
        return column;
    }
}
