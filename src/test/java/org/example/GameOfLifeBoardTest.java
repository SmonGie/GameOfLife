package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeBoardTest {

    private final PlainGameOfLifeSimulator simulator = new PlainGameOfLifeSimulator();

    @Test
    void testGameOfLifeBoardGeneration() {
        int n = 100;
        GameOfLifeBoard board1 = new GameOfLifeBoard(n, n);
        GameOfLifeBoard board2 = new GameOfLifeBoard(n, n);

        assertNotEquals(board1, board2);
    }

    @Test
    void testDoStep() {
        GameOfLifeBoard board = new GameOfLifeBoard(3, 3);
        boolean[][] initialState = {
                {true, false, true},
                {false, true, false},
                {true, true, false}
        };

        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 3][i % 3]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);

        board.doSimulationStep(simulator);
        boolean[][] expectedState = {
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedState.length * expectedState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedState[i / 3][i % 3]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void test3and4neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);
        board.doSimulationStep(simulator);
        boolean[][] expectedAfterStep = {
                {false, false, false, false},
                {true, true, true, false},
                {true, false, true, false},
                {true, true, true, false}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedAfterStep.length * expectedAfterStep[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedAfterStep[i / 4][i % 4]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void test2neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {true, true, true, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);
        board.doSimulationStep(simulator);
        boolean[][] expectedAfterStep = {
                {true, true, true, true},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedAfterStep.length * expectedAfterStep[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedAfterStep[i / 4][i % 4]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void test1neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, true, false},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);
        board.doSimulationStep(simulator);
        boolean[][] expectedAfterStep = {
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedAfterStep.length * expectedAfterStep[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedAfterStep[i / 4][i % 4]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void test0neigh() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, false, false, false},
                {false, true, false, false},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
            expC.setState(false);
            listExpState.add(expC);
        }
        board.setCustomBoard(listInitState);
        board.doSimulationStep(simulator);
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void testEdgeConditions() {
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
        board.doSimulationStep(simulator);
        boolean[][] expectedAfterStep = {
                {true, false, false, true},
                {false, false, false, false},
                {false, false, false, false},
                {true, false, false, true}
        };
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < expectedAfterStep.length * expectedAfterStep[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(expectedAfterStep[i / 4][i % 4]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }


    @Test
    public void testSetGetBoard() {
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
        List<GameOfLifeCell> listExpState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listExpState.add(expC);
        }
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    public void testDisplay() {
        GameOfLifeBoard board = new GameOfLifeBoard(4, 4);
        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, false, false},
                {true, true, true, false},
                {false, true, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        board.setCustomBoard(listInitState);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        board.showBoard();
        System.setOut(originalOut);
        String output = outputStream.toString();
        String expectedOutput = ". . . . " + System.lineSeparator()
                + ". O . . " + System.lineSeparator()
                + "O O O . " + System.lineSeparator()
                + ". O . . " + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    void testDoSimulationStep() {
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

        board.doSimulationStep(simulator);

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
        for (int i = 0; i < board.getBoard().size(); i++)
            assertEquals(listExpState.get(i).getState(), board.getBoard().get(i).getState());
    }

    @Test
    void testGetGameOfLifeRow() {
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

        List<GameOfLifeCell> resultRow = board.getGameOfLifeRow(3);

        List<GameOfLifeCell> expectedRow = new ArrayList<GameOfLifeCell>();
        for (int i = 0; i < 4; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            if (i == 0 || i == 3)
                expC.setState(true);
            else
                expC.setState(false);
            expectedRow.add(expC);
        }
        for (int i = 0; i < resultRow.size(); i++)
            assertEquals(expectedRow.get(i).getState(), resultRow.get(i).getState());
    }

    @Test
    void testGetGameOfLifeColumn() {
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

        List<GameOfLifeCell> resultColumn = board.getGameOfLifeColumn(1);

        List<GameOfLifeCell> expectedColumn = new ArrayList<GameOfLifeCell>();

        for (int i = 0; i < 4; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(false);
            expectedColumn.add(expC);
        }
        for (int i = 0; i < resultColumn.size(); i++)
            assertEquals(expectedColumn.get(i).getState(), resultColumn.get(i).getState());
    }

    @Test
    void testNeighborsInitialization() {
        GameOfLifeBoard board = new GameOfLifeBoard(3, 3);

        GameOfLifeCell centerCell = board.getCell(1, 1);

        int[][] expectedNeighbors = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

        assertEquals(expectedNeighbors.length, centerCell.getNeighbors().size());

        for (int[] position : expectedNeighbors) {
            GameOfLifeCell neighborCell = board.getCell(position[0], position[1]);
            assertTrue(centerCell.getNeighbors().contains(neighborCell));
        }
    }
}