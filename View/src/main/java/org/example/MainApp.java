package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/MyView.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
