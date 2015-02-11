package xpeppers.training.kata2;

import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

public class MapOfValues{
	private Map<String,String> valueMap;
	
	protected MapOfValues(){
		valueMap = new TreeMap<>();
	}
	
	protected void addVariable(String varName, String varContent){
		valueMap.put(varName, varContent);
	}
	
	protected String getVariable(String varName){
		if (valueMap.containsKey(varName))
			return valueMap.get(varName);
		return "";
	}
	
	protected Iterator<String> getIterator(){
		return valueMap.keySet().iterator();
	}
	
}
