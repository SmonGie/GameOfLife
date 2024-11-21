package org.example;

/*-
 * #%L
 * ModelProject
 * %%
 * Copyright (C) 2024 Szymon Giergiel and Tomasz Genderka
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
