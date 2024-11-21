package org.example;

/*-
 * #%L
 * GameOfLife
 * %%
 * Copyright (C) 2024 Szymon Giergiel and Tomasz Genderka
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameOfLifeBoard board = null;
        System.out.print("Czy chcesz wczytać planszę z pliku? (tak/nie): ");
        PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();
        String response = scanner.next();
        if (response.equalsIgnoreCase("tak")) {
            board = loadBoardFromFile(scanner);
        }

        if (board == null) {
            int width = getPositiveInteger(scanner, "Podaj liczbę wierszy planszy: ");
            int height = getPositiveInteger(scanner, "Podaj liczbę kolumn planszy: ");
            board = new GameOfLifeBoard(width, height);
        }

        int generations = getPositiveInteger(scanner, "Podaj liczbę pokoleń do symulacji: ");

        runSimulation(board, simulator, generations);

        System.out.print("Czy chcesz zapisać początkową planszę do pliku? (tak/nie): ");
        if (scanner.next().equalsIgnoreCase("tak")) {
            System.out.print("Podaj nazwę pliku: ");
            String fileName = scanner.next();
            try (Dao<GameOfLifeBoard> dao = GameOfLifeBoardDaoFactory.createFileDao(fileName)) {
                dao.write(board);
                System.out.println("Plansza zapisana do pliku: " + fileName);
            } catch (Exception e) {
                System.err.println("Nie udało się zapisać planszy: " + e.getMessage());
            }
        }

    }

    private static void runSimulation(GameOfLifeBoard board, PlainGameOfLifeSimulator simulator, int generations) {
        for (int i = 0; i < generations; i++) {
            System.out.println("\n==== Pokolenie " + (i + 1) + " ====");
            board.showBoard();
            board.doSimulationStep(simulator);
        }
        System.out.println("\n==== Symulacja zakończona ====");
    }

    private static int getPositiveInteger(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Wartość musi być większa od zera.");
                }
            } else {
                System.out.println("Proszę podać liczbę całkowitą.");
                scanner.next();
            }
        }
        return value;
    }

    private static GameOfLifeBoard loadBoardFromFile(Scanner scanner) {
        GameOfLifeBoard board = null;
        boolean continueTrying = true;
        while (continueTrying) {
            System.out.print("Podaj nazwę pliku (lub wpisz 'wyjdz', aby podać wymiary planszy): ");
            String fileName = scanner.next();

            if (fileName.equalsIgnoreCase("wyjdz")) {
                continueTrying = false;
            } else {
                try (Dao<GameOfLifeBoard> dao = GameOfLifeBoardDaoFactory.createFileDao(fileName)) {
                    board = dao.read();
                    System.out.println("Plansza wczytana z pliku: " + fileName);
                    continueTrying = false;
                } catch (Exception e) {
                    System.err.println("Nie udało się wczytać planszy z pliku. Spróbuj ponownie.");
                }
            }
        }
        return board;
    }
}
