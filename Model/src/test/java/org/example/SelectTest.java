package org.example;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5434/kompo";
        String user = "postgres";
        String password = "haslo";
        String query1 = "SELECT * FROM GameOfLifeBoard";
        String query2 = "SELECT * FROM GameOfLifeCell";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query1)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("name");
                String width = rs.getString("width");
                String height = rs.getString("height");
                System.out.println("ID: " + id + ", Name: " + name + ", Size: " + width + "x" + height);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query2)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String value = rs.getString("value");
                String board = rs.getString("board_id");
                int x = rs.getInt("xpos");
                int y = rs.getInt("ypos");
                System.out.println("ID: " + id + ", Value: " + value + ", Board ID: " + board
                        + ", X:" + x + ", Y:" + y);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void CheckSavedBoard() {
        GameOfLifeBoard originalBoard = new GameOfLifeBoard(4, 4);

        boolean[][] initialState = {
                {false, false, false, false},
                {false, true, true, false},
                {false, true, true, false},
                {false, false, false, false}
        };
        List<GameOfLifeCell> listInitState = new ArrayList<>();
        for (int i = 0; i < initialState.length * initialState[0].length; i++) {
            GameOfLifeCell expC = new GameOfLifeCell();
            expC.setState(initialState[i / 4][i % 4]);
            listInitState.add(expC);
        }
        originalBoard.setCustomBoard(listInitState);

        String boardName = "123";
        originalBoard.setName(boardName);
        try (JpaGameOfLifeBoardDao dao = new JpaGameOfLifeBoardDao()) {
            dao.write(originalBoard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:5434/kompo";
        String user = "postgres";
        String password = "haslo";
        String query1 = "SELECT * FROM GameOfLifeBoard WHERE name = ?";
        String query2 = "SELECT * FROM GameOfLifeCell";
        int w = 4;
        int h = 4;
        String bid = "1";
        GameOfLifeBoard tempB = new GameOfLifeBoard(w, h);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query1)) {

            pstmt.setString(1, boardName);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                assertFalse(true);
            }

            if (rs.next()) {
                String boardId = rs.getString("id");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                w = width;
                h = height;
                bid = boardId;
                tempB = new GameOfLifeBoard(width, height);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query2)) {

            while (rs.next()) {
                int x = rs.getInt("xpos");
                int y = rs.getInt("ypos");
                boolean value = rs.getBoolean("value");
                String boardId = rs.getString("board_id");
                if (boardId.equals(bid)) {
                    tempB.getCell(x, y).setState(value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < listInitState.size(); i++) {
            assertEquals(originalBoard.getCell(i % w, i % h), tempB.getCell(i % w, i % h));
        }
    }
}
