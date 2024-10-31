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
    }
}
