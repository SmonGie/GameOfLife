package org.example;

import java.util.ListResourceBundle;

public class Messages extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "gameOfLifeLabel", "Gra życia" },
                { "boardSizeLabel", "Wymiary planszy:" },
                { "densityLabel", "Zagęszczenie:" },
                { "startButton", "Start" },
                { "saveBoardButton", "Zapisz planszę" },
                { "loadBoardButton", "Odczytaj planszę" }
        };
    }
}
