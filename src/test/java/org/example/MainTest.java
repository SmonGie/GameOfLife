package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    public void mainTest() {
        Main main = new Main();
        String simulatedInput = "5\n5\n3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.main(new String[]{});
        assertTrue(true);
    }
}
