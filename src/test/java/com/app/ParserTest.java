package test.java.com.app;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.java.com.app.Parser;
import main.java.com.app.exceptions.DelimitadorInvalidoException;
import main.java.com.app.exceptions.InvalidDisplayOptionException;
import main.java.com.app.Reader;

class ParserTest {

	@Test
	public void testIfThereIsDelimiter() {
		Parser parser = new Parser();
		
		assertNotNull(parser.getDelimiter());
	}
	
	@ParameterizedTest
	@CsvSource({
		";, ;",
		"., .",
		"*, *",
		"-, -"
	})
	public void testIfChosenDelimiterIsCorrect(String input, String expectedOutput) throws DelimitadorInvalidoException {
		Parser parser = new Parser(input);
		
		assertEquals(parser.getDelimiter(), expectedOutput);
	}
	
	@ParameterizedTest
	@CsvSource({
		"hello",
		"testing",
		"third time's a charm"
	})
	public void testInvalidDelimiter(String input) {
		assertThrows(DelimitadorInvalidoException.class, () -> {
			new Parser(input);
		});
	}
	
	@ParameterizedTest
	@CsvSource({
		"\\n, \\n",
		"\\t, \\t",
		"\\r, \\r"
	})
	public void testSpecialCharDelimiter(String input, String expectedOutput) throws DelimitadorInvalidoException {
		Parser parser = new Parser(input);
		
		assertEquals(parser.getDelimiter(), expectedOutput);
	}
	
	@Test
	public void testIfThereIsReader() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		assertTrue(parser.getReader() instanceof Reader);
	}
	
	@Test
	public void testIfThereIsReadLines() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String[] lines = parser.getReader().read();
		
		assertTrue(lines[0] instanceof String);
		assertTrue(lines[1] instanceof String);
		assertTrue(lines[2] instanceof String);
	}
	
	@Test
	public void testIfThereIsEvolutionsMatrix() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<ArrayList<String>> evo = new ArrayList<ArrayList<String>> ();
		evo.add(new ArrayList<String>());
		
		assertTrue(evo.get(0) instanceof ArrayList<?>);
	}
	
	@Test
	public void testIfReturnsFirstEvolution() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parser.parse();
		
		ArrayList<String> evolution0 = parser.getEvolution(0);
		ArrayList<String> expected = new ArrayList<String>();
		
		int values[] = {1110, 3200, 934, 2310, 3178, 4009, 737, 3121, 1976, 2573, 6291};
		for(int value : values) {
			expected.add("" + value);
		}
		
		assertIterableEquals(expected, evolution0);
	}
	
	@Test
	public void testIfReturnsSecondEvolution() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parser.parse();
		
		ArrayList<String> evolution0 = parser.getEvolution(1);
		ArrayList<String> expected = new ArrayList<String>();
		
		int values[] = {413, 577, 410, 584, 866, 1075, 400, 701, 506, 720, 539};
		for(int value : values) {
			expected.add("" + value);
		}
		
		assertIterableEquals(expected, evolution0);
	}
	
	@Test
	public void testIfReturnsLastEvolution() {
		Parser parser = new Parser();
		try {
			parser.setReader(new Reader("analysisTime.out"));		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		parser.parse();
		
		ArrayList<String> evolution0 = parser.getEvolution(20);
		ArrayList<String> expected = new ArrayList<String>();
		
		int values[] = {892820, 850745, 878276, 877227, 876456, 866763, 858800, 860945, 868931, 883683, 867026};
		for(int value : values) {
			expected.add("" + value);
		}
		
		assertIterableEquals(expected, evolution0);
	}
	
	@ParameterizedTest
	@CsvSource({
		"linhas",
		"colunas"
	})
	public void testIfThereIsDisplayOption(String input) throws InvalidDisplayOptionException{
		Parser parser = new Parser();
		parser.setDisplayOption(input);			
		
		assertEquals(parser.getDisplayOption(), input);
	}
	
	@ParameterizedTest
	@CsvSource({
		"texto",
		"espaços",
		"\\t"
	})
	public void testInvalidDisplayOption(String input){
		Parser parser = new Parser();
		
		assertThrows(InvalidDisplayOptionException.class, () -> {
			parser.setDisplayOption(input);
		});
	}
}
