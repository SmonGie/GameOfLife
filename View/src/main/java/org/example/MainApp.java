package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/MyView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Game of Life");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
