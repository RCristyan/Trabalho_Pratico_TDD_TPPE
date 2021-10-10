package com.app;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterTest {
    @Test
    public void testWritableOutputPath(){
        // Arrange
        Writer writer = new Writer();
        boolean permicao = false;
        String currentPath = Path.of("").toAbsolutePath().toString();

        // Act
        writer.setOutputPath(currentPath);
        permicao = writer.pathAllowWrite(writer.getOutputPath());

        // Assert
        assertEquals(true, permicao);
    }
}