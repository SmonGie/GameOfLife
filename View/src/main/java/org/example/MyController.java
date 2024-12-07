package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MyController {
    @FXML
    private TextField sizeField;

    @FXML
    private ComboBox<Density> comboBox = new ComboBox<>();

    @FXML
    public void initialize() {
        comboBox.getItems().setAll(Density.values());
        comboBox.setValue(Density.LOW);
    }

    public void startSimulation() {
        String sizeText = sizeField.getText();

        try {
            int size = Integer.parseInt(sizeText);
            if (size < 4 || size > 20) {
                showError("Rozmiar planszy musi być między 4 a 20.");
                return;
            }

            GameOfLifeBoard initialBoard = new GameOfLifeBoard(size, size);
            initializeBoardDensity(initialBoard, comboBox.getValue().toString());

            showBoard(initialBoard);
        } catch (NumberFormatException e) {
            showError("Nieprawidłowy format danych");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showBoard(GameOfLifeBoard board) {
        Stage stage = new Stage();
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(15));

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Button cellButton = new Button();
                GameOfLifeCell cell = board.getCell(i, j);

                cellButton.setText(cell.getCellValue() ? "O" : ".");
                cellButton.setMinSize(30, 30);

                layout.add(cellButton, j, i);
            }
        }

        Scene scene = new Scene(layout, 700, 700);
        stage.setScene(scene);
        stage.setTitle("Plansza");
        stage.show();
    }

    private void initializeBoardDensity(GameOfLifeBoard board, String density) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                board.getCell(i, j).setState(false);
            }
        }
        Density boardDensity = Density.fromString(density);
        Random rand = new Random();
        int numCellsToLive = (int) (board.getWidth() * board.getHeight() * boardDensity.getPercentage() / 100.0f);
        Set<String> liveCellsSet = new HashSet<>();
        while (liveCellsSet.size() < numCellsToLive) {
            int row = rand.nextInt(board.getHeight());
            int col = rand.nextInt(board.getWidth());
            String cellKey = row + " " + col;

            if (!liveCellsSet.contains(cellKey)) {
                board.getCell(row, col).setState(true);
                liveCellsSet.add(cellKey);
            }
        }
    }
}
