package org.example;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;


    public class GameOfLifeCellAdapter {
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
            cell.setState(!cell.getState());
            cellStateProperty.set(cell.getState());
        }

        public void updateButtonAppearance(Boolean isAlive) {
            button.setText(isAlive ? "Alive" : "Dead");
            button.setStyle(isAlive ? "-fx-background-color: green;" : "");
        }
    }

