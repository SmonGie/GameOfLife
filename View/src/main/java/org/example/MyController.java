package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    private Button doStepButton;
    @FXML
    private ComboBox<String> languageComboBox;

    private ResourceBundle bundle;

    private GameOfLifeBoard clonedBoard;
    private GameOfLifeBoard currentBoard;

    @FXML
    private Text author1Text;

    @FXML
    private Text author2Text;

    private ResourceBundle authorsBundle;

    private GridPane layout;

    private PlainGameOfLifeSimulator simulator;

    @FXML
    public void initialize() {
        comboBox.getItems().setAll(Density.values());
        translateDensityComboBox();
        comboBox.setValue(Density.LOW);

        languageComboBox.setValue("en");
        changeLanguage("en");

        authorsBundle = ResourceBundle.getBundle("org.example.AuthorsResourceBundle");
        author1Text.setText(authorsBundle.getString("author1"));
        author2Text.setText(authorsBundle.getString("author2"));
        simulator = new PlainGameOfLifeSimulator();
    }

    private void translateDensityComboBox() {
        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Density density) {
                if (density == null) {
                    return "";
                }
                switch (density) {
                    case LOW:
                        return bundle.getString("densityLOW");
                    case MEDIUM:
                        return bundle.getString("densityMEDIUM");
                    case HIGH:
                        return bundle.getString("densityHIGH");
                    default:
                        return density.toString();
                }
            }

            @Override
            public Density fromString(String string) {
                if (string.equals(bundle.getString("densityLOW"))) {
                    return Density.LOW;
                } else if (string.equals(bundle.getString("densityMEDIUM"))) {
                    return Density.MEDIUM;
                } else if (string.equals(bundle.getString("densityHIGH"))) {
                    return Density.HIGH;
                }
                return null;
            }
        });
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
        sizeField.setPromptText(bundle.getString("sizeField"));

        translateDensityComboBox();
        Density currentValue = comboBox.getValue();
        comboBox.setValue(null);
        comboBox.setValue(currentValue);
    }

    public void startSimulation() {
        String sizeText = sizeField.getText();

        try {
            int size = Integer.parseInt(sizeText);
            if (size < 4 || size > 20) {
                showError("sizeERROR");
                return;
            }

            currentBoard = new GameOfLifeBoard(size, size);
            initializeBoardDensity(currentBoard, comboBox.getValue().toString());
            clonedBoard = new GameOfLifeBoard(currentBoard);
            showBoard(currentBoard);
        } catch (NumberFormatException e) {
            showError("dataFormatERROR");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ErrorTitle"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(message));
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("InfoTitle"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(message));
        alert.showAndWait();
    }

    private void showBoard(GameOfLifeBoard board) {
        Stage stage = new Stage();
        layout = new GridPane();
        layout.setPadding(new Insets(15));
        layout.setHgap(3);
        layout.setVgap(3);

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Button cellButton = new Button();
                GameOfLifeCell cell = board.getCell(i, j);

                cellButton.setMinSize(30, 30);

                GameOfLifeCellAdapter adapter = new GameOfLifeCellAdapter(cell, cellButton);
                adapter.updateButtonAppearance(cell.getState());
                layout.add(cellButton, j, i);

            }
        }

        Button doStepButton = new Button("Do Step");
        doStepButton.setOnAction(event -> doStep(board));

        GridPane.setConstraints(doStepButton, 0, board.getHeight());
        layout.add(doStepButton, 0, board.getHeight(), board.getWidth(), 1);

        Scene scene = new Scene(layout, 700, 700);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("BoardTitle"));
        stage.show();
    }

    private void doStep(GameOfLifeBoard board) {
        board.doSimulationStep(simulator);
        updateBoardDisplay(board);
    }

    private void updateBoardDisplay(GameOfLifeBoard board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Button cellButton = (Button) layout.getChildren().get(i * board.getWidth() + j);
                GameOfLifeCell cell = board.getCell(i, j);
                GameOfLifeCellAdapter adapter = new GameOfLifeCellAdapter(cell, cellButton);
                adapter.updateButtonAppearance(cell.getState());
            }
        }
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

    @FXML
    public void saveBoard() {
        if (currentBoard == null) {
            showError("noBoardERROR");
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
                    showInfo("saveSuccessInfo");
                } catch (IOException e) {
                    showError("saveBoardERROR");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void loadBoard() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (FileGameOfLifeBoardDao dao = new FileGameOfLifeBoardDao(file.getAbsolutePath())) {
                currentBoard = dao.read();
                showBoard(currentBoard);
                showInfo("loadSuccessInfo");
            } catch (IOException e) {
                showError("loadBoardERROR");
            } catch (IllegalArgumentException e) {
                showError("loadBoardERROR");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
