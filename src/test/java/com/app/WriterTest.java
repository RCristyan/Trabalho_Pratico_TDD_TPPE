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

    @ParameterizedTest
    @CsvSource({
            "file1.txt",
            "file2.txt",
            "file3.txt"
    })
    public void testWriterIsCreatingFile(String file){
        Writer writer = new Writer();
        try {
            writer.setOutputPath("src/test/resources/temp");
            writer.write(file, "Hello World!");
        } catch(Exception ex){
            fail(ex.getMessage());
        }

        Path path = Paths.get("src/test/resources/temp/"+file);
        assertTrue(path.toFile().exists());

        // Clean
        path.toFile().delete();
    }

    @Test
    public void TestWriterIsWritingContent() {
        Writer writer = new Writer();
        try {
            writer.setOutputPath("src/test/resources");
            writer.write("file.txt", "Hello World!");
        } catch(Exception ex){
            fail(ex.getMessage());
        }
        Path path = Paths.get("src/test/resources/file.txt");
        BufferedReader reader;
        String content = "";
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
            content = reader.readLine();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals("Hello World!", content);

        // Clean
        path.toFile().delete();
    }
}