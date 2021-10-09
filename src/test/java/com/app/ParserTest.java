package test.java.com.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.com.app.Parser;
import main.java.com.app.test;

class ParserTest {

	@Test
	public void testIfThereIsDelimiter() {
		Parser parser = new Parser();
		
		assertNotNull(parser.getDelimiter());
	}
	
}
