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


import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;

public class GameOfLifeCell extends AbstractRoot {
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

    @Override
    public String toString() {
        Set<GameOfLifeCell> visited = new HashSet<>();
        return toStringHelper(visited);
    }

    private String toStringHelper(Set<GameOfLifeCell> visited) {
        if (!visited.add(this)) {
            return this.getClass().getSimpleName() + " {value: " + value + ", neighbors: [...], observers: [...] }";
        }

        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " {");
        result.append("value: ").append(value);

        if (neighbors != null && !neighbors.isEmpty()) {
            result.append(", neighbors: [");
            result.append(neighbors.size());
            result.append("]");
        } else {
            result.append(", neighbors: []");
        }

        if (observers != null && !observers.isEmpty()) {
            result.append(", observers: [");
            StringJoiner sj = new StringJoiner(", ");
            for (Object observer : observers) {
                sj.add(observer.toString());
            }
            result.append(sj.toString());
            result.append("]");
        } else {
            result.append(", observers: []");
        }

        result.append("}");
        return result.toString();
    }




    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameOfLifeCell other = (GameOfLifeCell) obj;

        if (this.value != other.value) {
            return false;
        }

        if (this.neighbors.size() != other.neighbors.size()) {
            return false;
        }
        for (int i = 0; i < this.neighbors.size(); i++) {

            GameOfLifeCell neighbor1 = this.neighbors.get(i);
            GameOfLifeCell neighbor2 = other.neighbors.get(i);

            if (neighbor1.getCellValue() != neighbor2.getCellValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder result = new HashCodeBuilder(17, 37)
                .append(value);

        for (GameOfLifeCell neighbor : neighbors) {
            result.append(neighbor.getCellValue());
        }

        return result.toHashCode();
    }

}
