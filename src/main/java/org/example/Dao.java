package org.example;

public interface Dao<T> extends AutoCloseable {
    T read() throws Exception;

    void write(T obj) throws Exception;
}

