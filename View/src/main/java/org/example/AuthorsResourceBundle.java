package org.example;

import java.util.ListResourceBundle;

public class AuthorsResourceBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"author1", "Tomasz Genderka"},
                {"author2", "Szymon Giergiel"}
        };
    }
}
