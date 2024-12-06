package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameOfLifeView extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        Label titleLabel = new Label("Game of Life");
        Label setSize = new Label("Wymiary planszy:");
        TextField sizeField = new TextField();
        sizeField.setPromptText("Wymiary planszy (min 4, max 20)");

        Label densityLabel = new Label("Zagęszczenie:");
        TextField densityField = new TextField();
        densityField.setPromptText("Wybierz zagęszczenie: mały, średni, duży");

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            String sizeText = sizeField.getText();
            String densityText = densityField.getText();
            try {
                int size = Integer.parseInt(sizeText);
                if (size < 4 || size > 20) {
                    showError("Rozmiar planszy musi być między 4 a 20.");
                } else {
                    startSimulation(size, densityText);
                }
            } catch (NumberFormatException e) {
                showError("Nieprawidłowy format danych");
            }
        });

        layout.getChildren().addAll(titleLabel, setSize, sizeField, densityLabel, densityField, startButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void startSimulation(int size, String density) {
        Density boardDensity = Density.fromString(density);

        if (boardDensity == Density.LOW && !density.equalsIgnoreCase("mały")) {
            density = "mały";
            showError("Nieprawidłowe zagęszczenie. Użyto domyślnego: mały.");
        }

        System.out.println("Start symulacji z wymiarami: " + size + " i zageszczeniem: " + density);
        GameOfLifeBoard initialBoard = new GameOfLifeBoard(size, size);
        initializeBoardDensity(initialBoard, density);
        showBoard(initialBoard);
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

        Scene scene = new Scene(layout, 500, 450);
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
        int numCellsToLive = (board.getWidth() * board.getHeight() * boardDensity.getPercentage()) / 100;

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

    public static void main(String[] args) {
        launch(args);
    }
}
