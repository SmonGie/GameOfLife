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
        PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();
        GameOfLifeBoard board = new GameOfLifeBoard(width, height);

        runSimulation(board, simulator, generations);

    }

    private static void runSimulation(GameOfLifeBoard board, PlainGameOfLifeSimulator simulator, int generations) {
        for (int i = 0; i < generations; i++) {
            System.out.println("Generation " + (i + 1));
            board.showBoard();
            board.doSimulationStep(simulator);
        }
    }



}
