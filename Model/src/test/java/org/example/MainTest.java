package org.example;

/*-
 * #%L
 * GameOfLife
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


import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    public void mainTest() {
        String simulatedInput = "nie\n5\n5\n3\nnie\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("==== Pokolenie 1 ===="));
        assertTrue(output.contains("==== Pokolenie 2 ===="));
        assertTrue(output.contains("==== Pokolenie 3 ===="));

        assertTrue(output.contains("O") || output.contains("."));
    }

    @Test
    public void mainTest_one_oneBoard() {
        String simulatedInput = "nie\n5\n5\n3\nnie\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main mainx = new Main();
        mainx.main(new String[]{});
        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("Pokolenie"));
    }


}
