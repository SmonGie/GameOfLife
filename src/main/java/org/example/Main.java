package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        GameOfLifeBoard board = new GameOfLifeBoard(width, height);
        board.showBoard();

    }
}
