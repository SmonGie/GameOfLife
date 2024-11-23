package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class GameOfLifeBoardDaoFactoryTest {
    @Test
    void DaoFactory() {
        GameOfLifeBoardDaoFactory factory = new GameOfLifeBoardDaoFactory();
        try {
            assertTrue(factory.createFileDao("DaoTest").getClass().equals(FileGameOfLifeBoardDao.class));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}
