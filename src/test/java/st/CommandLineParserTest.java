package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CommandLineParserTest {

	private Parser parser;
	
	@Before
	public void setUp() {
		parser = new Parser();
	}
	
	@Test
	public void example() {

		assertEquals(true, true);

	}
}
