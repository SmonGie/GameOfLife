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


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeObserverTest {

    private GameOfLifeBoard board;
    private GameOfLifeRow row;
    private GameOfLifeColumn column;

    @BeforeEach
    public void setUp() {
        board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);

        List<GameOfLifeCell> rowCells = new ArrayList<>();
        List<GameOfLifeCell> colCells = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            rowCells.add(board.getCell(1, i));
            colCells.add(board.getCell(i, 1));
        }

        row = new GameOfLifeRow(rowCells);
        column = new GameOfLifeColumn(colCells);
    }

    @Test
    public void testObserverNotificationStateChange() {

        int initialAliveCountRow = row.countAliveCells();
        int initialAliveCountColumn = column.countAliveCells();

        GameOfLifeCell centerCell = board.getCell(1, 1);
        centerCell.updateState(true);
        board.getCell(1, 2).updateState(true);
        int newAliveCountRow = row.countAliveCells();
        int newAliveCountColumn = column.countAliveCells();

        assertEquals(initialAliveCountRow + 2, newAliveCountRow);
        assertEquals(initialAliveCountColumn + 1, newAliveCountColumn);
    }

}
