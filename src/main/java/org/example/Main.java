package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj liczbę wierszy planszy: ");
        int width = scanner.nextInt();
        System.out.print("Podaj liczbę kolumn planszy: ");
        int height = scanner.nextInt();
        System.out.print("Podaj liczbę pokoleń do symulacji: ");
        int generations = scanner.nextInt();
        GameOfLifeBoard board = new GameOfLifeBoard(height, width);
        board.simulate(generations);
    }
}
