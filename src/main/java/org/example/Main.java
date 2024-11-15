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
