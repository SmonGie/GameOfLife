package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOfLifeCell {
    private boolean value;
    private List<GameOfLifeCell> neighbors;
    private List<CellObserver> observers;

    public GameOfLifeCell() {
        Random rand = new Random();
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
        return neighbors;
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
        if (value) {
            return aliveNeighbors == 2 || aliveNeighbors == 3;
        } else {
            return aliveNeighbors == 3;
        }
    }

    public void updateState(boolean newState) {
        this.value = newState;
        notifyObservers();
    }
}
