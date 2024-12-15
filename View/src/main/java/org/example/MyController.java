package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MyController {
    @FXML
    private Label gameOfLifeLabel;
    @FXML
    private Label boardSizeLabel;
    @FXML
    private TextField sizeField;
    @FXML
    private Label densityLabel;
    @FXML
    private ComboBox<Density> comboBox;
    @FXML
    private Button startButton;
    @FXML
    private Button saveBoardButton;
    @FXML
    private Button loadBoardButton;

    @FXML
    private ComboBox<String> languageComboBox;

    private ResourceBundle bundle;

    private GameOfLifeBoard clonedBoard;
    private GameOfLifeBoard currentBoard;

    @FXML
    public void initialize() {
        comboBox.getItems().setAll(Density.values());
        comboBox.setValue(Density.LOW);

        languageComboBox.setValue("en");
        changeLanguage("en");
    }

    public void changeLanguageFromComboBox() {
        String selectedLanguage = languageComboBox.getValue();
        changeLanguage(selectedLanguage);
    }

    public void changeLanguage(String languageCode) {
        Locale newLocale = new Locale(languageCode);
        bundle = ResourceBundle.getBundle("org.example.Messages", newLocale);
        updateUI();
    }

    private void updateUI() {
        gameOfLifeLabel.setText(bundle.getString("gameOfLifeLabel"));
        boardSizeLabel.setText(bundle.getString("boardSizeLabel"));
        densityLabel.setText(bundle.getString("densityLabel"));
        startButton.setText(bundle.getString("startButton"));
        saveBoardButton.setText(bundle.getString("saveBoardButton"));
        loadBoardButton.setText(bundle.getString("loadBoardButton"));
    }

    public void startSimulation() {
        String sizeText = sizeField.getText();

        try {
            int size = Integer.parseInt(sizeText);
            if (size < 4 || size > 20) {
                showError("Rozmiar planszy musi być między 4 a 20.");
                return;
            }

            currentBoard = new GameOfLifeBoard(size, size);
            initializeBoardDensity(currentBoard, comboBox.getValue().toString());
            clonedBoard = new GameOfLifeBoard(currentBoard);
            showBoard(currentBoard);
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

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
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

                final int row = i;
                final int col = j;

                cellButton.setOnAction(e -> {
                    board.toggleCellState(row, col);
                    updateBoardDisplay(board, layout);
                    // System.out.println(board.getBoard());
                });

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

    private void updateBoardDisplay(GameOfLifeBoard board, GridPane layout) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Button cellButton = (Button) layout.getChildren().get(i * board.getWidth() + j);
                GameOfLifeCell cell = board.getCell(i, j);
                cellButton.setText(cell.getCellValue() ? "O" : ".");
            }
        }
    }

    @FXML
    public void saveBoard() {
        if (currentBoard == null) {
            showError("Nie utworzono jeszcze planszy.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                String originalFileName = file.getAbsolutePath() + ".original";
                try (FileGameOfLifeBoardDaoOriginalState dao = new FileGameOfLifeBoardDaoOriginalState(
                        file.getAbsolutePath(), originalFileName)) {
                    dao.write(currentBoard);
                    dao.saveOriginalBoard(clonedBoard);
                    showInfo("Plansza została zapisana wraz z oryginalnym stanem.");
                }
            } catch (IOException e) {
                showError("Wystąpił błąd podczas zapisywania planszy.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void loadBoard() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (FileGameOfLifeBoardDao dao = new FileGameOfLifeBoardDao(file.getAbsolutePath())) {
                currentBoard = dao.read();
                showBoard(currentBoard);
                showInfo("Plansza została wczytana.");
            } catch (IOException e) {
                showError("Wystąpił błąd podczas wczytywania planszy.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
