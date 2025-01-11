package org.example;

/*-
 * #%L
 * ModelProject
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileGameOfLifeBoardTest {
    @Test
    void testFileGameOfLifeBoardWR() {
        GameOfLifeBoardDaoFactory factory = new GameOfLifeBoardDaoFactory();
        try {
            GameOfLifeBoard board = new GameOfLifeBoard(3, 3);
            boolean[][] initialState = {
                    {true, false, false},
                    {false, false, false},
                    {false, false, false}
            };
            List<GameOfLifeCell> listInitState = new ArrayList<>();
            for (int i = 0; i < initialState.length * initialState[0].length; i++) {
                GameOfLifeCell expC = new GameOfLifeCell();
                expC.setState(initialState[i / 3][i % 3]);
                listInitState.add(expC);
            }
            board.setCustomBoard(listInitState);
            FileGameOfLifeBoardDao fileDao = (FileGameOfLifeBoardDao) factory.createFileDao("DaoTestRead");
            fileDao.write(board);
            assertEquals(board, fileDao.read());
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(false);
            listInitState.set(0, expC);
            board.setCustomBoard(listInitState);
            assertNotEquals(board, fileDao.read());
        } catch (Exception e) {
            fail("exception thrown: " + e.getMessage());
        }
    }

//    @Test
//    void testAutoCloseable() throws exception {
//        GameOfLifeBoardDaoFactory factory = new GameOfLifeBoardDaoFactory();
//        GameOfLifeBoard board = new GameOfLifeBoard(3, 3);
//        boolean[][] initialState = {
//                {true, false, false},
//                {false, false, false},
//                {false, false, false}
//        };
//        List<GameOfLifeCell> listInitState = new ArrayList<>();
//        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
//            GameOfLifeCell expC = new GameOfLifeCell();
//            expC.setState(initialState[i / 3][i % 3]);
//            listInitState.add(expC);
//        }
//        board.setCustomBoard(listInitState);
//        FileGameOfLifeBoardDao fileDao = spy((FileGameOfLifeBoardDao) factory.createFileDao("DaoTestClose"));
//        try (fileDao) {
//            fileDao.write(board);
//            GameOfLifeBoard readBoard = fileDao.read();
//            assertNotNull(readBoard);
//        }
//        verify(fileDao).close();
//    }
}
