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


public class PlainGameOfLifeSimulator implements GameOfLifeSimulator<GameOfLifeBoard> {
    @Override
    public void doStep(GameOfLifeBoard golb) {
        boolean[][] tempGolCells = new boolean[golb.getWidth()][golb.getHeight()];
        for (int i = 0; i < golb.getWidth(); i++) {
            for (int j = 0; j < golb.getHeight(); j++) {
                tempGolCells[i][j] = golb.getCell(i, j).nextState();
            }
        }
        for (int i = 0; i < golb.getWidth(); i++) {
            for (int j = 0; j < golb.getHeight(); j++) {
                golb.getCell(i, j).updateState(tempGolCells[i][j]);
            }
        }
    }
}
