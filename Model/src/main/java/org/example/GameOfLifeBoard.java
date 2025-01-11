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
import org.apache.commons.lang3.builder.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class GameOfLifeBoard implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameOfLifeCell> board;
    @Column(nullable = false)
    int width;
    @Column(nullable = false)
    int height;

    public GameOfLifeBoard(int width, int height) {
        this.width = width;
        this.height = height;
        initializeCells();
        initializeNeighbors();
    }

    public GameOfLifeBoard(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        initializeCells();
        initializeNeighbors();
    }

    public GameOfLifeBoard() {

    }

    public GameOfLifeBoard(GameOfLifeBoard other) {
        this.width = other.width;
        this.height = other.height;
        this.board = new ArrayList<>();
        for (GameOfLifeCell cell : other.board) {
            this.board.add(new GameOfLifeCell(cell.getCellValue()));
        }
    }

    private void initializeCells() {
        List<GameOfLifeCell> cellList = new ArrayList<>();
        for (int i = 0; i < width * height; i++) {
            cellList.add(new GameOfLifeCell());
        }
        Collections.shuffle(cellList);
        board = Collections.unmodifiableList(cellList);
    }

    private void initializeNeighbors() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int neighborRow = (i + k + width) % width;
                        int neighborCol = (j + l + height) % height;
                        board.get(i * height + j).addNeighbor(board.get(neighborRow * height + neighborCol));
                    }
                }
            }
        }
    }

    public String getName() {
       return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void showBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(board.get(i * height + j).getCellValue() ? "O " : ". ");
            }
            System.out.println();
        }
    }

    public List<GameOfLifeCell> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public void setCustomBoard(List<GameOfLifeCell> customBoard) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board.get(i * width + j).updateState(customBoard.get(i * width + j).getCellValue());
            }
        }
    }

    public void doSimulationStep(GameOfLifeSimulator<GameOfLifeBoard> simulator) {
        simulator.doStep(this);
    }

    public List<GameOfLifeCell> getGameOfLifeRow(int rowIndex) {
        return Collections.unmodifiableList(board.subList(rowIndex * width, (rowIndex + 1) * width));
    }

    public GameOfLifeCell getCell(int row, int col) {
        return board.get(row * height + col);
    }


    public List<GameOfLifeCell> getGameOfLifeColumn(int columnIndex) {
        List<GameOfLifeCell> column = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            column.add(getCell(i, columnIndex));
        }
        return Collections.unmodifiableList(column);

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " {");
        result.append("board: [");

        StringJoiner sj = new StringJoiner(", ");
        for (GameOfLifeCell cell : board) {
            sj.add("GameOfLifeCell {value: " + cell.getCellValue() + "}");
        }
        result.append(sj);
        result.append("], ");

        result.append("width: ").append(width);
        result.append(", height: ").append(height);
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
        GameOfLifeBoard other = (GameOfLifeBoard) obj;
        return new EqualsBuilder()
                .append(this.board, other.board)
                .append(this.width, other.width)
                .append(this.height, other.height)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(board)
                .append(width)
                .append(height)
                .toHashCode();
    }

    public static GameOfLifeBoard fromString(String data) {
        String[] lines = data.split("\n");
        int height = lines.length;
        int width = lines[0].length();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                throw new IllegalArgumentException("Wiersze nie mogą być puste.");
            }
            if (line.length() != width) {
                throw new IllegalArgumentException("Wszystkie wiersze muszą mieć tę samą długość.");
            }
        }

        List<GameOfLifeCell> cells = new ArrayList<>();
        for (String line : lines) {
            for (char ch : line.toCharArray()) {
                if (ch != '1' && ch != '0') {
                    throw new IllegalArgumentException("Plik musi zawierać tylko '1' i '0' bez odstępów.");
                }
                cells.add(new GameOfLifeCell(ch == '1'));
            }
        }

        GameOfLifeBoard board = new GameOfLifeBoard(height, width);
        board.setCustomBoard(cells);
        return board;
    }

    @Override
    public GameOfLifeBoard clone() {
        try {
            GameOfLifeBoard cloned = (GameOfLifeBoard) super.clone();

            List<GameOfLifeCell> clonedBoard = new ArrayList<>(this.board.size());
            for (GameOfLifeCell cell : this.board) {
                clonedBoard.add(new GameOfLifeCell(cell.getCellValue()));
            }

            cloned.board = clonedBoard;

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
}
