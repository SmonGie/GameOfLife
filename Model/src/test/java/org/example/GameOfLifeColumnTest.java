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


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeColumnTest {
    @Test
    void testCountAliveCellsInColumn() {
        List<GameOfLifeCell> cells = new ArrayList<>(Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        ));

        GameOfLifeColumn column = new GameOfLifeColumn(cells);
        assertEquals(2, column.countAliveCells());
    }

    @Test
    void testCountDeadCellsInRow() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeColumn column = new GameOfLifeColumn(cells);
        assertEquals(2, column.countDeadCells());
    }

}
