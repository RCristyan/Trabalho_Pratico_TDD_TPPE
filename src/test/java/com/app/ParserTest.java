package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.app.exceptions.DelimitadorInvalidoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	
}
