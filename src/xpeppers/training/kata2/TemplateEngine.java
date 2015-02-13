package xpeppers.training.kata2;

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

class TemplateEngine {
	public TemplateEngine() {}
	
	public String evaluate(String input, MapOfValues variableMap){
		input = parseComplexVariablesInText(input, variableMap, "\\$\\{\\$", "\\}");
		input = parseComplexVariablesInText(input, variableMap,    "\\{\\$", "\\}");
		return input;
	}
	//
	//SUBS
	//
	
	private String parseComplexVariablesInText(String input, MapOfValues variableMap, String prefix, String suffix, Boolean replaceValue) {
		variableMap.initializeIterator();
		
		while(variableMap.hasNext()){
			String varName = variableMap.getNextElement();
			
			String variableRegex = prefix + varName + suffix;
			
			String replaceValue = variableMap.getVariable(varName);
			
			Pattern pattern = Pattern.compile(variableRegex);
	        Matcher matcher = pattern.matcher(input);
	        if(matcher.find())
	        	input = matcher.replaceAll(replaceValue);
	       // else
	        //	throw new IllegalArgumentException("Missing Value Exception");
		}
		return input;
	}
	
	protected String doublesBracketsWhenPreceededByDollar(String input, MapOfValues variableMap){
		variableMap.initializeIterator();
		
		while(variableMap.hasNext()){
			String varName = variableMap.getNextElement();
			String replaceValue = "\\${{\\$" + varName + "}}";
			String variableRegex = "\\$\\{\\$" + varName + "\\}";
			Pattern pattern = Pattern.compile(variableRegex);
	        Matcher matcher = pattern.matcher(input);
	        input = matcher.replaceAll(replaceValue);
		}
		return input;
	}
}
