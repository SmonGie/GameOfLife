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


import jakarta.persistence.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.*;

@Entity
public class GameOfLifeCell implements Serializable, Comparable<GameOfLifeCell>, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    int xpos;
    @Column(nullable = false)
    int ypos;

    @Column(nullable = false)
    private boolean value;

    @Transient
    private List<GameOfLifeCell> neighbors;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private GameOfLifeBoard board;

    @Transient
    private List<CellObserver> observers;
    private static final Random rand = new Random();

    public GameOfLifeCell() {
        value = rand.nextBoolean();
        neighbors = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public GameOfLifeCell(boolean initialState) {
        this.value = initialState;
        this.neighbors = new ArrayList<>();
        this.observers = new ArrayList<>();
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

    public String toString() {
        return toStringHelper();
    }

    private String toStringHelper() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " {");
        result.append("value: ").append(value);

        if (!neighbors.isEmpty()) {
            result.append(", neighbors: [");
            result.append(neighbors.size());
            result.append("]");
        } else {
            result.append(", neighbors: []");
        }

        if (!observers.isEmpty()) {
            result.append(", observers: [");
            StringJoiner sj = new StringJoiner(", ");
            for (Object observer : observers) {
                sj.add(observer.toString());
            }
            result.append(sj);
            result.append("]");
        } else {
            result.append(", observers: []");
        }

        result.append("}");
        return result.toString();
    }

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

    public int hashCode() {
        HashCodeBuilder result = new HashCodeBuilder(17, 37)
                .append(value);

        for (GameOfLifeCell neighbor : neighbors) {
            result.append(neighbor.getCellValue());
        }

        return result.toHashCode();
    }

    @Override
    public int compareTo(GameOfLifeCell other) {
        if (other == null) {
            return 1;
        }
        return Boolean.compare(this.value, other.value);
    }

    @Override
    public GameOfLifeCell clone() {
        try {
            GameOfLifeCell cloned = (GameOfLifeCell) super.clone();

            cloned.neighbors = new ArrayList<>(this.neighbors);
            cloned.observers = new ArrayList<>(this.observers);

            List<GameOfLifeCell> clonedNeighbors = new ArrayList<>();
            for (GameOfLifeCell neighbor : this.neighbors) {
                clonedNeighbors.add(neighbor.clone());
            }
            cloned.neighbors = clonedNeighbors;

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("CloneNotSupportedException", e);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameOfLifeBoard getBoard() {
        return board;
    }

    public void setBoard(GameOfLifeBoard board) {
        this.board = board;
    }

    public void setPos(int x, int y) {
        xpos = x;
        ypos = y;
    }
}
