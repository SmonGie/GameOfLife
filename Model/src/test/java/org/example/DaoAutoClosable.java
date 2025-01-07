package org.example;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class DaoAutoClosable {

    @Test
    void testAutoCloseable() throws Exception {
        @SuppressWarnings("unchecked")
        Dao<String> mockDao = mock(Dao.class);
        try (mockDao) {
            mockDao.write("Test");
            mockDao.read();
        }
        verify(mockDao).write("Test");
        verify(mockDao).read();
        verify(mockDao).close();
    }

}
