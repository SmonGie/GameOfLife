package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
//import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.exception.BoardSizeException;
import org.example.exception.GameOfLifeBoardException;
import org.example.exception.InvalidActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.io.File;
//import java.io.IOException;
import java.sql.*;
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
    @FXML
    private TextField boardNameField;

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

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

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
                throw new BoardSizeException("Size must be between 4 and 20");
            }

            if (comboBox.getValue() == null) {
                throw new InvalidActionException("Density must be selected.");
            }

            currentBoard = new GameOfLifeBoard(size, size);
            initializeBoardDensity(currentBoard, comboBox.getValue().toString());
            clonedBoard = new GameOfLifeBoard(currentBoard);
            showBoard(currentBoard);
        } catch (NumberFormatException e) {
            logger.error("Invalid number format encountered: {}", e.getMessage());
            showError("dataFormatERROR");
        } catch (InvalidActionException e) {
            logger.error("Invalid action error: {}", e.getMessage());
            showError("invalidActionError");
        } catch (BoardSizeException e) {
            logger.error("Board size error: {}", e.getMessage());
            showError("sizeERROR");
        } catch (GameOfLifeBoardException e) {
            logger.error("Game board initialization failed: {}", e.getMessage(), e);
            showError("boardInitializationError");
        }
    }

    private void showError(String message) {
        logger.error("Displaying error message: {}", message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("ErrorTitle"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(message));
        alert.showAndWait();
    }

    private void showInfo(String message) {
        logger.info("Displaying info message: {}", message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("InfoTitle"));
        alert.setHeaderText(null);
        alert.setContentText(bundle.getString(message));
        alert.showAndWait();
    }

    private void showBoard(GameOfLifeBoard board) {
        logger.info("Displaying the board with size {}x{}", board.getHeight(), board.getWidth());
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
        Stage stage = new Stage();
        Scene scene = new Scene(layout, 700, 700);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("BoardTitle"));
        stage.show();
    }

    private void doStep(GameOfLifeBoard board) {
        logger.info("Performing a simulation step.");
        board.doSimulationStep(simulator);
        updateBoardDisplay(board);
    }

    private void updateBoardDisplay(GameOfLifeBoard board) {
        logger.info("Updating board display.");
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Button cellButton = (Button) layout.getChildren().get(i * board.getWidth() + j);
                GameOfLifeCell cell = board.getCell(i, j);
                GameOfLifeCellAdapter adapter = new GameOfLifeCellAdapter(cell, cellButton);
                adapter.updateButtonAppearance(cell.getState());
            }
        }
    }

    private void initializeBoardDensity(GameOfLifeBoard board, String density) throws GameOfLifeBoardException {
        logger.info("Initializing board density for density: {}", density);
        if (board == null) {
            throw new GameOfLifeBoardException("Board cannot be null.");
        }
        if (density == null || density.isEmpty()) {
            throw new GameOfLifeBoardException("Density must be specified.");
        }
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                GameOfLifeCell cell = board.getCell(i, j);
                cell.setState(false);
                cell.setBoard(board);
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
            logger.warn("Attempted to save board, but no board is currently set.");
            showError("noBoardERROR");
            return;
        }

        String boardName = boardNameField.getText();
        if (boardName == null || boardName.isEmpty()) {
            logger.warn("Attempted to save board, but no board name is provided.");
            showError("noBoardNameERROR");
            return;
        }

        currentBoard.setName(boardName);
        try (JpaGameOfLifeBoardDao dao = new JpaGameOfLifeBoardDao()) {
            dao.write(currentBoard);
            logger.info("Board saved successfully to the database.");
            showInfo("saveSuccessInfo");
        } catch (Exception e) {
            logger.error("Error saving the board to the database.", e);
            showError("saveBoardERROR");
        }
    }

    @FXML
    public void loadBoard() {
        String url = "jdbc:postgresql://localhost:5434/kompo";
        String user = "postgres";
        String password = "haslo";
        String boardName = boardNameField.getText();
        String query1 = "SELECT * FROM GameOfLifeBoard WHERE name = ?";
        String query2 = "SELECT * FROM GameOfLifeCell";
        int w = 4;
        int h = 4;
        String bid = "1";
        GameOfLifeBoard tempB = new GameOfLifeBoard(w, h);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query1)) {

            pstmt.setString(1, boardName);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                logger.error("Error loading the board from db");
                showError("loadBoardERROR");
                return;
            }

            if (rs.next()) {
                String boardId = rs.getString("id");
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                w = width;
                h = height;
                bid = boardId;
                tempB = new GameOfLifeBoard(width, height);
            }

        } catch (Exception e) {
            logger.error("Unexpected error during board load process", e);
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query2)) {

            while (rs.next()) {
                int x = rs.getInt("xpos");
                int y = rs.getInt("ypos");
                boolean value = rs.getBoolean("value");
                String boardId = rs.getString("board_id");
                if (boardId.equals(bid)) {
                    tempB.getCell(x, y).setState(value);
                }
            }
        } catch (Exception e) {
            logger.error("Unexpected error during cell load process", e);
            throw new RuntimeException(e);
        }
        logger.info("Board loaded successfully from db");
        currentBoard = tempB;
        showBoard(currentBoard);
        showInfo("loadSuccessInfo");

    }

}
