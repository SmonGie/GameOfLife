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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeCellTest {

    @Test
    void addNeighbor() {
        GameOfLifeCell cell1 = new GameOfLifeCell();
        GameOfLifeCell cell2 = new GameOfLifeCell();

        cell1.addNeighbor(cell2);

        assertTrue(cell1.getNeighbors().contains(cell2));
    }

    @Test
    void getCellValue() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(false);

        boolean value = cell.getCellValue();

        assertFalse(value);
    }

    @Test
    void nextState() {
        GameOfLifeCell cell = new GameOfLifeCell();

        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        GameOfLifeCell neighbor3 = new GameOfLifeCell();

        neighbor1.updateState(false);
        neighbor2.updateState(false);
        neighbor3.updateState(false);

        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);
        cell.addNeighbor(neighbor3);

        boolean nextState = cell.nextState();

        assertFalse(nextState);

        cell.updateState(true);

        neighbor1.updateState(true);
        neighbor2.updateState(true);
        assertTrue(cell.nextState());
    }

    @Test
    void updateState() {
        GameOfLifeCell cell = new GameOfLifeCell();

        boolean initialValue = cell.getCellValue();
        boolean newValue = !initialValue;

        cell.updateState(newValue);
        assertEquals(cell.getCellValue(), newValue);

    }

    @Test
    void nextStateWithThreeLiveNeighbors() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(true);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        GameOfLifeCell neighbor3 = new GameOfLifeCell();
        neighbor3.updateState(true);

        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);
        cell.addNeighbor(neighbor3);

        assertTrue(cell.nextState());
    }

    @Test
    void nextStateWithTwoLiveNeighbors() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(true);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        GameOfLifeCell neighbor3 = new GameOfLifeCell();
        neighbor3.updateState(false);

        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);
        cell.addNeighbor(neighbor3);

        assertTrue(cell.nextState());
    }

    @Test
    void nextStateWithThreeDeadNeighbors() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(false);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        GameOfLifeCell neighbor3 = new GameOfLifeCell();
        neighbor3.updateState(false);
        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);
        cell.addNeighbor(neighbor3);

        boolean nextState = cell.nextState();
        assertFalse(nextState);
    }

    @Test
    void nextStateWithTwoTwoLiveNeighbors() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(true);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);

        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);

        boolean nextState = cell.nextState();
        assertTrue(nextState);
    }

    @Test
    void nextStateWithFewerThanTwoLiveNeighbors() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(true);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(false);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(false);

        assertFalse(cell.nextState());
    }

    @Test
    void observerAddRemove() {
        GameOfLifeCell cell = new GameOfLifeCell();
        List<GameOfLifeCell> cellList = new ArrayList<>();
        cellList.add(cell);
        GameOfLifeColumn golColumn = new GameOfLifeColumn(cellList);
        cell.removeObserver(golColumn);
        assertEquals(cellList.getFirst().getObservers(), cell.getObservers());
    }

    @Test
    void testToString() {
        GameOfLifeCell cell1 = new GameOfLifeCell();
        cell1.updateState(false);
        String expectedResult = "GameOfLifeCell {value: false, neighbors: [], observers: []}";
        assertEquals(expectedResult, cell1.toString());
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        cell1.addNeighbor(neighbor1);
        cell1.addNeighbor(neighbor2);
        expectedResult = "GameOfLifeCell {value: false, neighbors: [2], observers: []}";
        assertEquals(expectedResult, cell1.toString());
        List<GameOfLifeCell> cellList = new ArrayList<>();
        cellList.add(cell1);
        GameOfLifeColumn golColumn = new GameOfLifeColumn(cellList);
        expectedResult = "GameOfLifeCell {value: false, neighbors: [2], observers: [org.example.GameOfLifeColumn[cells=[GameOfLifeCell {value: false, neighbors: [2], observers: [org.example.GameOfLifeColumn[cells=java.util.ImmutableCollections$List]]}]]]}";
        assertEquals(expectedResult.toString().substring(0, 86), cell1.toString().substring(0, 86));
        
    }

    @Test
    void testEquals() {
        GameOfLifeCell cell1 = new GameOfLifeCell();
        GameOfLifeCell cell2 = new GameOfLifeCell();
        cell1.updateState(false);
        cell2.updateState(false);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        GameOfLifeCell neighbor3 = new GameOfLifeCell();
        neighbor2.updateState(true);
        cell1.addNeighbor(neighbor1);
        cell1.addNeighbor(neighbor2);
        cell2.addNeighbor(neighbor1);
        cell2.addNeighbor(neighbor2);
        assertTrue(cell1.equals(cell2));
        cell2.updateState(true);
        assertFalse(cell1.equals(cell2));
        GameOfLifeBoard q = new GameOfLifeBoard(1, 1);
        assertFalse(cell1.equals(q));
        GameOfLifeCell q2 = null;
        assertFalse(cell1.equals(q2));
        cell2.updateState(false);
        cell2.addNeighbor(neighbor3);
        assertFalse(cell1.equals(cell2));
    }

    @Test
    void testHashCode() {
        GameOfLifeCell cell1 = new GameOfLifeCell();
        GameOfLifeCell cell2 = new GameOfLifeCell();
        cell1.updateState(false);
        cell2.updateState(false);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(true);
        cell1.addNeighbor(neighbor1);
        cell1.addNeighbor(neighbor2);
        cell2.addNeighbor(neighbor1);
        cell2.addNeighbor(neighbor2);
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }


}
