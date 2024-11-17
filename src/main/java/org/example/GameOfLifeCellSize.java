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


//import org.apache.commons.lang3.builder.EqualsBuilder;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Objects;

public abstract class GameOfLifeCellSize<T extends GameOfLifeCell> {
    protected List<T> cells;

    public GameOfLifeCellSize(List<T> cells) {
        this.cells = List.copyOf(Objects.requireNonNull(cells));
    }

    public int countAliveCells() {
        int count = 0;
        for (T cell : cells) {
            if (cell.getCellValue()) {
                count++;
            }
        }
        return count;
    }

    public int countDeadCells() {
        return cells.size() - countAliveCells();
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("cells", cells)
                .toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null) {
            return false;//&& getClass() == obj.getClass();
        }
        return false;
        /*GameOfLifeCellSize<?> other = (GameOfLifeCellSize<?>) obj;
        return Objects.equals(this.cells, other.cells);*/
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(cells)
                .toHashCode();
    }

}
