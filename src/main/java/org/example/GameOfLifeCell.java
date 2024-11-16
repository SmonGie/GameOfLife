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


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
        ToStringBuilder result = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("value", value);
        for (Object neighbour : neighbors) {
            result.append(neighbour.getClass().getName() + "@"
                    + Integer.toHexString(System.identityHashCode(neighbour)));
        }
        result.append("observers", observers);
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
        EqualsBuilder result = new EqualsBuilder()
                .append(this.value, other.value)
                .append(this.neighbors, other.neighbors)
                .append(this.observers, observers);
        return result.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder result = new HashCodeBuilder(17, 37)
                .append(value);
        for (Object neighbour : neighbors) {
            result.append(neighbour.getClass().getName() + "@"
                    + Integer.toHexString(System.identityHashCode(neighbour)));
        }
        result.append(observers);
        return result.toHashCode();
    }

}
