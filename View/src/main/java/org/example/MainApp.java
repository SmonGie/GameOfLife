package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class MainApp extends Application {

    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            logger.info("Starting Game of Life application.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/MyView.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Game of Life");
            primaryStage.show();

            logger.info("FXML loaded and stage is displayed.");
        } catch (Exception e) {
            logger.error("An error occurred while loading the application: {}", e.getMessage(), e);
            throw e;
        }
    }

    public static void main(String[] args) {
        logger.info("Launching Game of Life application.");
        launch(args);
    }
}
