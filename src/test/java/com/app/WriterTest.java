package com.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    @Test
    public void testWritableOutputPath(){
        // Arrange
        Writer writer = new Writer();
        boolean permicao = false;
        String messageError = null;
        String currentPath = Path.of("").toAbsolutePath().toString();

        // Act
        try {
            writer.setOutputPath(currentPath);
            permicao = writer.pathAllowWrite(writer.getOutputPath());
        } catch (Exception ex){
            permicao = false;
            messageError = ex.getMessage();
        }

        // Assert
        assertEquals(true, permicao);
        assertNull(messageError);
    }

    @Test
    public void testNotWritableOutputPath(){
        // Arrange
        Writer writer = new Writer();
        boolean permicao = false;
        String messageError = null;
        String currentPath = Path.of("").toAbsolutePath().toString() + Path.of("/output/random");

        // Act
        try {
            writer.setOutputPath(currentPath);
            permicao = writer.pathAllowWrite(writer.getOutputPath());
        } catch (Exception ex){
            permicao = false;
            messageError = ex.getMessage();
        }

        // Assert
        assertEquals(false, permicao);
        assertEquals("Não há permição de escrita no caminho de destino!", messageError);
    }

    @Test
    public void testOutputFormatIsLine() {
        // Arrange
        Writer writer = new Writer();
        String input = "1";
        boolean formatoSaidaDefinido = false;

        // Act
        formatoSaidaDefinido = writer.defineFormatoSaida(new Scanner(input));

        // Assert
        assertEquals("linha", writer.getFormatoSaida());
        assertEquals(true, formatoSaidaDefinido);
    }

    @Test
    public void testOutputFormatIsColumn() {
        // Arrange
        Writer writer = new Writer();
        String input = "2";
        boolean formatoSaidaDefinido = false;

        // Act
        formatoSaidaDefinido = writer.defineFormatoSaida(new Scanner(input));

        // Assert
        assertEquals("coluna", writer.getFormatoSaida());
        assertEquals(true, formatoSaidaDefinido);
    }
    
    @ParameterizedTest
    @CsvSource({
            "5",
            "10",
            "10000"
    })
    public void testOutputFormatIsNull(String input) {
        // Arrange
        Writer writer = new Writer();
        boolean formatoSaidaDefinido = false;

        // Act
        formatoSaidaDefinido = writer.defineFormatoSaida(new Scanner(input));

        // Assert
        assertNull(writer.getFormatoSaida());
        assertEquals(false, formatoSaidaDefinido);
    }

    @Test
    public void testWriterIsWriting(){
        Writer writer = new Writer();
        try {
            writer.setOutputPath("src/test/resources");
            writer.write("file.txt", "Hello World!");
        } catch(Exception ex){
            fail(ex.getMessage());
        }

        Path path = Paths.get("src/test/resources/file.txt");
        assertTrue(path.toFile().exists());
    }

    @Test
    public void testWriterIsWriting2(){
        Writer writer = new Writer();
        try {
            writer.setOutputPath("src/test/resources");
            writer.write("file2.txt", "Hello World!");
        } catch(Exception ex){
            fail(ex.getMessage());
        }

        Path path = Paths.get("src/test/resources/file2.txt");
        assertTrue(path.toFile().exists());
    }
}