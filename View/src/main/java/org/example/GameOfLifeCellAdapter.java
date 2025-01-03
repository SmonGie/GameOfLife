package org.example;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameOfLifeCellAdapter {
        private static final Logger logger = LoggerFactory.getLogger(GameOfLifeCellAdapter.class);
        private final GameOfLifeCell cell;
        private final Button button;

        private final BooleanProperty cellStateProperty = new SimpleBooleanProperty();

        public GameOfLifeCellAdapter(GameOfLifeCell cell, Button button) {
            this.cell = cell;
            this.button = button;

            cellStateProperty.addListener((obs, oldVal, newVal) -> updateButtonAppearance(newVal));
            cellStateProperty.set(cell.getState());

            button.setOnAction(e -> toggleCellState());
        }

        private void toggleCellState() {
            logger.info("Toggling cell state: current state = {}", cell.getState());
            cell.setState(!cell.getState());
            cellStateProperty.set(cell.getState());
            logger.info("New cell state = {}", cell.getState());
        }

        public void updateButtonAppearance(Boolean isAlive) {
            logger.info("Updating button appearance: cell is {}", isAlive ? "Alive" : "Dead");
            button.setText(isAlive ? "Alive" : "Dead");
            button.setStyle(isAlive ? "-fx-background-color: green;" : "");
        }
    }

