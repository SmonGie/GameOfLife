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

public class PlainGameOfLifeSimulatorTest {
    private final PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();

    @Test
    public void testDoStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };

        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);

        simulator.doStep(board);

        boolean[][] expectedState = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedState.length * expectedState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedState[i / 4][i % 4]);
            listExpState.add(expC);
        }
        System.out.println(listExpState);
        System.out.println(board.getBoard());
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void testToString() {
        PlainGameOfLifeSimulator s1 = new PlainGameOfLifeSimulator();
        PlainGameOfLifeSimulator s2 = new PlainGameOfLifeSimulator();
        assertEquals(s1.toString().substring(0, 36), s2.toString().substring(0, 36));
    }

    @Test
    public void testEquals() {
        PlainGameOfLifeSimulator s1 = new PlainGameOfLifeSimulator();
        PlainGameOfLifeSimulator s2 = new PlainGameOfLifeSimulator();
        assertTrue(s1.equals(s1));
        assertTrue(s1.equals(s2));
        GameOfLifeCell c1 = new GameOfLifeCell();
        assertFalse(s1.equals(c1));
        PlainGameOfLifeSimulator s3 = null;
        assertFalse(s1.equals(s3));
    }

    @Test
    void testHashCode() {
        PlainGameOfLifeSimulator s1 = new PlainGameOfLifeSimulator();
        PlainGameOfLifeSimulator s2 = new PlainGameOfLifeSimulator();
        assertEquals(s1.hashCode(), s2.hashCode());
        GameOfLifeCell c1 = new GameOfLifeCell();
        assertNotEquals(s1.hashCode(), c1.hashCode());
    }
}
