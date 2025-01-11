package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaGameOfLifeBoardDao implements Dao<GameOfLifeBoard>, AutoCloseable {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public JpaGameOfLifeBoardDao() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public GameOfLifeBoard read() throws Exception {
        try {
            entityManager.getTransaction().begin();
            List<GameOfLifeBoard> boards = entityManager.createQuery("SELECT b FROM GameOfLifeBoard b",
                    GameOfLifeBoard.class).getResultList();
            entityManager.getTransaction().commit();
            if (boards.isEmpty()) {
                throw new Exception("No GameOfLifeBoard found in the database.");
            }
            return boards.getFirst();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void write(GameOfLifeBoard board) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(board);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}