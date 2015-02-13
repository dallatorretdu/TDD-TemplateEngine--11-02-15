package xpeppers.training.kata2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TemplateEngineTest {
	private VariablesMap testMap;
	
	@Before
	public void initializeMap() {
		testMap = new VariablesMap();
	}
	
	@Test
	public void mapAcceptsNewVariableWithoutException() {
		testMap.addVariable("name", "Mario");
	}


	@Test
	public void mapRecordsNewVariables() {
		testMap.addVariable("name", "Mario");
		testMap.addVariable("surname", "Rossi");
		assertEquals("Mario", testMap.getVariable("name"));
		assertEquals("Rossi", testMap.getVariable("surname"));
	};
	
	@Test
	public void evaluatesStringWithNoVariable() {
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("A plain, clear string", testMap);
		assertEquals("A plain, clear string", output);
	};
	
	@Test
	public void evaluatesStringWithOneVariable() {
		testMap.addVariable("name", "Mario");
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("Good evening {$name}, how are you feeling today?", testMap);
		assertEquals("Good evening Mario, how are you feeling today?", output);
	};
	
	@Test
	public void evaluatesStringWithOneComplexVariable() {
		testMap.addVariable("dogName_Full", "Lucky Luke");
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("How is {$dogName_Full} doing?", testMap);
		assertEquals("How is Lucky Luke doing?", output);
	};
	
	@Test
	public void evaluatesStringWithMultipleVariable() {
		testMap.addVariable("name", "Lucky");
		testMap.addVariable("surname", "Rossi");
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("Hello {$name}, {$surname}, how are you?", testMap);
		assertEquals("Hello Lucky, Rossi, how are you?", output);
	};
	
	@Test
	public void evaluatesStringWithMultipleExpressions() {
		testMap.addVariable("name", "Lucky");
		testMap.addVariable("surname", "Rossi");
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("Hello {$name}, {$surname}, how are you?", testMap);
		assertEquals("Hello Lucky, Rossi, how are you?", output);
	};
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionIfTemplateValueIsNotFoundInText() {
		ExpectedException thrown = ExpectedException.none();
		
		testMap.addVariable("rossiName", "Mario");
		testMap.addVariable("rossiSurname", "Rossi");
		TemplateEngine templateEngine = new TemplateEngine();

		thrown.expect(IllegalArgumentException.class);
		templateEngine.evaluate("Good Morning Mr {$rossiSurname}.", testMap);
	};
	
	@Test
	public void evaluatesComplexCases() {
		testMap.addVariable("name", "Lucky");
		TemplateEngine templateEngine = new TemplateEngine();
		String output = templateEngine.evaluate("Hello ${$name}, how are you?", testMap);
		assertEquals("Hello ${Lucky}, how are you?", output);
	};
	
	@Test
	public void canAlignComplexCases() {
		testMap.addVariable("name", "Lucky");
		TemplateEngine templateEngine = new TemplateEngine();
		assertEquals("${{$name}}", templateEngine.doublesBracketsWhenPreceededByDollar("${$name}",testMap));
	}
	
	@Test
	public void MapOfValuesCanIterateItsOwnContent() {
		testMap.addVariable("first", "Element");
		testMap.addVariable("second", "Content");
		testMap.initializeIterator();
		assertEquals("first", testMap.getNextElement());
		assertEquals("second", testMap.getNextElement());
		assertEquals("", testMap.getNextElement());
	}
}
