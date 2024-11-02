package org.example;


public class GameOfLifeBoard {
    private final GameOfLifeCell[][] board;

    public GameOfLifeBoard(int width, int height) {
        board = new GameOfLifeCell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new GameOfLifeCell();
            }
        }
        initializeNeighbors();
    }

    private void initializeNeighbors() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int neighborRow = (i + k + board.length) % board.length;
                        int neighborCol = (j + l + board[i].length) % board[i].length;
                        board[i][j].addNeighbor(board[neighborRow][neighborCol]);
                    }
                }
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getCellValue()  ? "O " : ". ");
            }
            System.out.println();
        }
    }

    public boolean[][] getBoard() {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[i][j] = board[i][j].getCellValue();
            }
        }
        return newBoard;
    }

    public void setCustomBoard(boolean[][] customBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].updateState(customBoard[i][j]);
            }
        }
    }

    public void doSimulationStep(GameOfLifeSimulator<GameOfLifeBoard> simulator) {
        simulator.doStep(this);
    }

    public boolean[] getGameOfLifeRow(int rowIndex) {
        boolean[] row = new boolean[board[rowIndex].length];
        for (int j = 0; j < board[rowIndex].length; j++) {
            row[j] = board[rowIndex][j].getCellValue();
        }
        return row;
    }

    public GameOfLifeCell getCell(int row, int col) {
        return board[row][col];
    }


    public boolean[] getGameOfLifeColumn(int columnIndex) {
        boolean[] column = new boolean[board.length];
        for (int i = 0; i < board.length; i++) {
            column[i] = board[i][columnIndex].getCellValue();
        }
        return column;
    }
}
