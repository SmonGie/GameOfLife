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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeCellSizeTest {

    @Test
    void countAliveCells() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize = new GameOfLifeCellSize<>(cells) {
        };
        assertEquals(2, cellSize.countAliveCells());
    }

    @Test
    void countDeadCells() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize = new GameOfLifeCellSize<>(cells) {
        };
        assertEquals(3, cellSize.countDeadCells());
    }

    @Test
    void testToString() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize1 = new GameOfLifeCellSize<>(cells) {
        };
        GameOfLifeCellSize<GameOfLifeCell> cellSize2 = new GameOfLifeCellSize<>(cells) {
        };
        assertEquals(cellSize1.toString().substring(0, 34) + cellSize1.toString().
                        substring(45, cellSize1.toString().length()),
                cellSize2.toString().substring(0, 34) + cellSize2.toString().
                        substring(45, cellSize2.toString().length()));
    }

    @Test
    void testEquals() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize1 = new GameOfLifeCellSize<>(cells) {
        };
        GameOfLifeCellSize<GameOfLifeCell> cellSize2 = cellSize1;
        GameOfLifeBoard q = new GameOfLifeBoard(1, 1);
        GameOfLifeBoard q2 = null;
        assertTrue(cellSize1.equals(cellSize2));
        assertFalse(cellSize1.equals(q));
        assertFalse(cellSize1.equals(q2));
        List<GameOfLifeCell> cellz = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(true)
        );
        GameOfLifeCellSize<GameOfLifeCell> cellSize4 = new GameOfLifeCellSize<>(cellz) {
        };
        assertFalse(cellSize1.equals(cellSize4));

    }

    @Test
    void testHashCode() {
        List<GameOfLifeCell> cells = Arrays.asList(
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(true),
                new TestGameOfLifeCellHelper(false),
                new TestGameOfLifeCellHelper(false)
        );

        GameOfLifeCellSize<GameOfLifeCell> cellSize1 = new GameOfLifeCellSize<>(cells) {
        };
        GameOfLifeCellSize<GameOfLifeCell> cellSize2 = new GameOfLifeCellSize<>(cells) {
        };
        assertEquals(cellSize1.hashCode(), cellSize2.hashCode());
    }
}
