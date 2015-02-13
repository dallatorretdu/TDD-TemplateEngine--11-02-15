package xpeppers.training.kata2;

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

class TemplateEngine {
	public TemplateEngine() {}
	
	public String evaluate(String input, VariablesMap variableMap){
		input = doublesBracketsWhenPreceededByDollar(input, variableMap);
		input = parseSimpleVariablesInText(input, variableMap);
		return input;
	}
	//
	//SUBS
	//
	private String parseSimpleVariablesInText(String input, VariablesMap variableMap) {
		variableMap.initializeIterator();
		
		while(variableMap.hasNext()){
			String varName = variableMap.getNextElement();
			String variableRegex = "\\{\\$" + varName + "\\}";
			String replaceValue = variableMap.getVariable(varName);
			input = executeRegexOnPattern(input, variableRegex, replaceValue);
		}
		return input;
	}
	
	protected String doublesBracketsWhenPreceededByDollar(String input, VariablesMap variableMap){
		variableMap.initializeIterator();
		
		while(variableMap.hasNext()){
			String varName = variableMap.getNextElement();
			String replaceValue = "\\${{\\$" + varName + "}}";
			String variableRegex = "\\$\\{\\$" + varName + "\\}";
			try{
				input = executeRegexOnPattern(input, variableRegex, replaceValue); }
			catch(IllegalArgumentException e){ }
		}
		return input;
	}
	
	private String executeRegexOnPattern(String input, String variableRegex,
			String replaceValue) {
		Pattern pattern = Pattern.compile(variableRegex);
		Matcher matcher = pattern.matcher(input);
		if(!matcher.find())
			throw new IllegalArgumentException("Missing Value Exception");
		input = matcher.replaceAll(replaceValue);
		return input;
	}
}
