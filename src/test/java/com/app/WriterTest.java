package com.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    
    @Test
    public void testOutputFormatIsNull() {
        // Arrange
        Writer writer = new Writer();
        String input = "5";
        boolean formatoSaidaDefinido = false;

        // Act
        formatoSaidaDefinido = writer.defineFormatoSaida(new Scanner(input));

        // Assert
        assertNull(writer.getFormatoSaida());
        assertEquals(false, formatoSaidaDefinido);
    }

    @Test
    public void testOutputFormatIsNull2() {
        // Arrange
        Writer writer = new Writer();
        String input = "10";
        boolean formatoSaidaDefinido = false;

        // Act
        formatoSaidaDefinido = writer.defineFormatoSaida(new Scanner(input));

        // Assert
        assertNull(writer.getFormatoSaida());
        assertEquals(false, formatoSaidaDefinido);
    }
}