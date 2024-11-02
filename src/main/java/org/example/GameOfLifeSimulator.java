package org.example;

public interface GameOfLifeSimulator<T extends GameOfLifeBoard> {
    void doStep(T board);
}
