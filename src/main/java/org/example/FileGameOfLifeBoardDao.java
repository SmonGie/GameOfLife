package org.example;

import java.io.*;

public class FileGameOfLifeBoardDao implements Dao<GameOfLifeBoard>, AutoCloseable {
    private final String fileName;
    private BufferedWriter writer;
    private BufferedReader reader;

    public FileGameOfLifeBoardDao(String fileName) throws Exception {
        this.fileName = fileName;
    }

    @Override
    public GameOfLifeBoard read() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder boardData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                boardData.append(line).append("\n");
            }
            return GameOfLifeBoard.fromString(boardData.toString());
        }
    }

    @Override
    public void write(GameOfLifeBoard board) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(board.toString());
        }
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
