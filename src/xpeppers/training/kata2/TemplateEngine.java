package xpeppers.training.kata2;

import java.util.regex.Pattern; 
import java.util.regex.Matcher;
import java.util.Iterator;

class TemplateEngine {
	public TemplateEngine() {}
	
	public String evaluate(String input, MapOfValues variableMap){
		input = doublesBracketsWhenPreceededByDollar(input, variableMap);
		
		Iterator<String> iterator = variableMap.getIterator();
		
		while(iterator.hasNext()){
			String varName = (String) iterator.next();
			
			String variableRegex = "\\{\\$" + varName + "\\}";
			String replaceValue = variableMap.getVariable(varName);;
			
			Pattern pattern = Pattern.compile(variableRegex);
	        Matcher matcher = pattern.matcher(input);
	        if(!matcher.find())
	        	throw new IllegalArgumentException("Missing Value Exception");
	        input = matcher.replaceAll(replaceValue);
		}
		return input;
	}
	
	protected String doublesBracketsWhenPreceededByDollar(String input, MapOfValues variableMap){
		Iterator<String> iterator = variableMap.getIterator();
		
		while(iterator.hasNext()){
			String varName = (String) iterator.next();
			String replaceValue = "\\${{\\$" + varName + "}}";
					
			String variableRegex = "\\$\\{\\$" + varName + "\\}";
			Pattern pattern = Pattern.compile(variableRegex);
	        Matcher matcher = pattern.matcher(input);
	        input = matcher.replaceAll(replaceValue);
		}
		return input;
	}
}
