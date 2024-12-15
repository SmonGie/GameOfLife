package org.example;

import java.io.*;

public class FileGameOfLifeBoardDaoOriginalState extends FileGameOfLifeBoardDao {
    private final String originalFileName;

    public FileGameOfLifeBoardDaoOriginalState(String fileName, String originalFileName) throws Exception {
        super(fileName);
        this.originalFileName = originalFileName;
    }

    @Override
    public void write(GameOfLifeBoard board) throws IOException {
        saveOriginalBoard(board);
        super.write(board);
    }

    public void saveOriginalBoard(GameOfLifeBoard board) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(originalFileName))) {
            for (int i = 0; i < board.getWidth(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    writer.write(board.getCell(i, j).getCellValue() ? "1" : "0");
                }
                writer.newLine();
            }
        }
    }

    public GameOfLifeBoard readOriginalBoard() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(originalFileName))) {
            StringBuilder boardData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                boardData.append(line).append("\n");
            }
            return GameOfLifeBoard.fromString(boardData.toString());
        }
    }
}
