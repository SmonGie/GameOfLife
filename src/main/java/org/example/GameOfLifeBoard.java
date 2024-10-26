package org.example;

import java.util.Random;

public class GameOfLifeBoard {
    private final boolean[][] board;

    public GameOfLifeBoard(int width, int height) {

        Random rand = new Random();
        board = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = rand.nextBoolean();
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] ? "O " : ". ");
            }
            System.out.println();
        }
    }

    public boolean[][] getBoard() {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }

    public void setCustomBoard(boolean[][] customBoard) {
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(customBoard[i], 0, board[i], 0, board[0].length);
        }
    }

    public void doSimulationStep(GameOfLifeSimulator simulator) {
        simulator.doStep(this);
    }
}
