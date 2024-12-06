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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameOfLifeRow<T extends GameOfLifeCell> extends GameOfLifeCellSize<T> implements CellObserver,
        Serializable, Cloneable {
    public GameOfLifeRow(List<T> cells) {
        super(cells);
        for (GameOfLifeCell cell : cells) {
            cell.addObserver(this);
        }
    }

    @Override
    public void update() {
        System.out.println("Wiersz zaktualizowany: Żywe komórki = " + countAliveCells() + ", Martwe komórki = "
                + countDeadCells());
    }

    @Override
    public GameOfLifeRow<T> clone() {
        try {
            GameOfLifeRow<T> cloned = (GameOfLifeRow<T>) super.clone();

            List<T> clonedCells = new ArrayList<>();
            for (T cell : getCells()) {
                clonedCells.add((T) cell.clone());
            }

            cloned.setCells(clonedCells);

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("CloneNotSupportedException", e);
        }
    }
}
