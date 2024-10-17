package org.example;

public class Main {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        GameOfLifeBoard board = new GameOfLifeBoard(width, height);
        board.showBoard();

    }
}
