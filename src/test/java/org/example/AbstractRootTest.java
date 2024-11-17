package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractRootTest {

    @Test
    void testGameOfLifeToString() {
        GameOfLifeBoard board = new GameOfLifeBoard(2, 2);
        boolean[][] initialState = {
                {true, false},
                {false, true}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 2][i % 2]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);
        String expectedResult = "GameOfLifeBoard {board: [GameOfLifeCell {value: true}, GameOfLifeCell {value: false}, GameOfLifeCell {value: false}, GameOfLifeCell {value: true}], width: 2, height: 2}";
        assertEquals(expectedResult, board.toString());
    }

    @Test
    void testGameOfLifeEqualsTrue() {
        GameOfLifeBoard board1 = new GameOfLifeBoard(3, 3);
        GameOfLifeBoard board2 = new GameOfLifeBoard(3, 3);
        boolean[][] initialState = {
                {true, false, false},
                {false, true, false},
                {false, false, true}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 3][i % 3]);
            listInitState.add(expC);
        }
        board1.setCustomBoard(listInitState);
        board2.setCustomBoard(listInitState);
        assertTrue(board1.equals(board2));
    }

    @Test
    void testGameOfLifeEqualsFalse() {
        GameOfLifeBoard board1 = new GameOfLifeBoard(3, 3);
        GameOfLifeBoard board2 = new GameOfLifeBoard(3, 3);
        boolean[][] initialState = {
                {true, false, false},
                {false, true, false},
                {false, false, true}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 3][i % 3]);
            listInitState.add(expC);
        }
        board1.setCustomBoard(listInitState);
        GameOfLifeCell expCe = new GameOfLifeCell();
        listInitState.set(0, expCe);
        expCe.setState(false);
        board2.setCustomBoard(listInitState);
        assertFalse(board1.equals(board2));
    }

    @Test
    void testGameOfLifeBoardHashCode() {
        GameOfLifeBoard board1 = new GameOfLifeBoard(3, 3);
        GameOfLifeBoard board2 = new GameOfLifeBoard(3, 3);
        boolean[][] initialState = {
                {true, false, false},
                {false, true, false},
                {false, false, true}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 3][i % 3]);
            listInitState.add(expC);
        }
        board1.setCustomBoard(listInitState);
        board2.setCustomBoard(listInitState);
        assertEquals(board1.hashCode(), board2.hashCode());

    }

    @Test
    void testGameOfLifeCellToString() {
        GameOfLifeCell cell = new GameOfLifeCell();
        cell.updateState(true);
        GameOfLifeCell neighbor1 = new GameOfLifeCell();
        neighbor1.updateState(true);
        GameOfLifeCell neighbor2 = new GameOfLifeCell();
        neighbor2.updateState(false);

        cell.addNeighbor(neighbor1);
        cell.addNeighbor(neighbor2);

        String expectedResult = "GameOfLifeCell {value: true, neighbors: [2], observers: []}";
        assertEquals(expectedResult, cell.toString());
    }
}
