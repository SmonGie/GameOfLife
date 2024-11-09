package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameOfLifeCell {
    private boolean value;
    private final List<GameOfLifeCell> neighbors;
    private final List<CellObserver> observers;
    private static final Random rand = new Random();

    public GameOfLifeCell() {
        value = rand.nextBoolean();
        neighbors = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addObserver(CellObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CellObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (CellObserver observer : observers) {
            observer.update();
        }
    }

    public void addNeighbor(GameOfLifeCell neighbor) {
        neighbors.add(neighbor);
    }

    public List<GameOfLifeCell> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    public boolean getCellValue() {
        return value;
    }

    public boolean nextState() {
        int aliveNeighbors = 0;
        for (GameOfLifeCell neighbor : neighbors) {
            if (neighbor.getCellValue()) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors == 3 || value && aliveNeighbors == 2;
    }

    public boolean getState() {
        return value;
    }

    public void setState(boolean value) {
        this.value = value;
        notifyObservers();
    }

    public void updateState(boolean newState) {
        this.value = newState;
        notifyObservers();
    }

    public List<CellObserver> getObservers() {
        return Collections.unmodifiableList(observers);
    }
}
