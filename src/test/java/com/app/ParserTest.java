package test.java.com.app;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.java.com.app.Parser;
import main.java.com.app.exceptions.DelimitadorInvalidoException;
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
		
		assertTrue(parser.getEvolutions() instanceof ArrayList<ArrayList>);
	}
}
