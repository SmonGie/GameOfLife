package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    void nextStateWithOneLiveNeighbors() {
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
        //assertEquals(cellList.getFirst().getObservers(), cell.getObservers());
        cell.removeObserver(golColumn);
        assertEquals(cellList.getFirst().getObservers(), cell.getObservers());
    }

}