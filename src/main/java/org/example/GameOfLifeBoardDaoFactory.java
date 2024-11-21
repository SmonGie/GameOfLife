package org.example;

public class GameOfLifeBoardDaoFactory {
    public static Dao<GameOfLifeBoard> createFileDao(String fileName) throws Exception {
        return new FileGameOfLifeBoardDao(fileName);
    }
}
