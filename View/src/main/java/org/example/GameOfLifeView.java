package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameOfLifeDisplay extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        Label titleLabel = new Label("Game of Life");
        Label setSize = new Label("Wymiary planszy:");
        TextField sizeField = new TextField();
        sizeField.setPromptText("Wymiary planszy");
        Label densityLabel = new Label("Zagęszczenie:");
        TextField densityField = new TextField();
        densityField.setPromptText("TEMP");
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            String sizeText = sizeField.getText();
            String densityText = densityField.getText();
            try {
                int size = Integer.parseInt(sizeText);
                int density = Integer.parseInt(densityText);
                if (size < 3) {
                    showError("Niepoprawny rozmiar");
                } else {
                    startSimulation(size, density);
                }
            } catch (NumberFormatException e) {
                showError("Nieprawidłowy format danych");
            }

        });
        layout.getChildren().addAll(titleLabel, setSize, sizeField, densityLabel, densityField);
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

    private void startSimulation(int size, int density) {
        System.out.println("Start:");
        System.out.println("Wymiary planszy: " + size);
        System.out.println("Temp: " + density);
        GameOfLifeBoard initialBoard = new GameOfLifeBoard(size, size);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
