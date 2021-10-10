package com.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReaderTests {
    @Test
    void testRead() {
        Reader reader = new Reader("src/test/resources/test.txt");
        String[] expected = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] actual = reader.read();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testReadNonExistingFile() {
        ArquivoNaoEncontradoException exception = assertThrows(ArquivoNaoEncontradoException.class, () -> {
            Reader reader = new Reader("src/test/resources/test-nonexist.txt");
            reader.read();
        });
        assertEquals("src/test/resources/test-nonexist.txt", exception.getMessage());
    }
}