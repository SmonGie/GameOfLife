package org.example;

import java.util.Random;

public class GameOfLifeBoard {
    private boolean[][] board;

    public GameOfLifeBoard(int width, int height) {
        Random rand = new Random();
        board = new boolean[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                board[i][j] = rand.nextBoolean();
            }
        }
    }
    public void showBoard()
    {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean[][] getBoard() {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[i].length);
        }
        return newBoard;
    }
    public void doStep()
    {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                int liveNeighbors = 0;
                    for(int k = -1; k <= 1; k++) {
                        for(int l = -1; l <= 1; l++) {
                            if(k == 0 && l == 0) continue;

                            int neighbourRow = (i+k+ board.length) % board.length;
                            int neighbourCol = (j+l+board[0].length) % board[0].length;
                            if(board[neighbourRow][neighbourCol]) {
                                liveNeighbors++;
                            }
                        }
                    }
                    if(board[i][j]) {
                        newBoard[i][j] = liveNeighbors == 3 || liveNeighbors == 2;
                    }
                    else {
                        newBoard[i][j] = liveNeighbors == 3;
                    }
            }
        }
        board = newBoard;
    }
}
