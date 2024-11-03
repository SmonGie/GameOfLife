package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    public void mainTest() {
        String simulatedInput = "5\n5\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Generation"));
        assertTrue(output.contains("Generation 1"));
        assertTrue(output.contains("Generation 2"));
        assertTrue(output.contains("Generation 3"));

        assertTrue(output.contains("O") || output.contains("."));
    }

    @Test
    public void mainTest_one_oneBoard() {
        String simulatedInput = "1\n1\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Main mainx = new Main();
        mainx.main(new String[]{});
        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("Generation"));
    }


}
