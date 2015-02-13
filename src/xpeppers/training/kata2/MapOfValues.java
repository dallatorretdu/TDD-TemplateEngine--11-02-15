package xpeppers.training.kata2;

import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;

public class MapOfValues{
	private Map<String,String> valueMap;
	private Iterator<String> iterator;
	
	protected MapOfValues(){
		valueMap = new TreeMap<String, String>();
	}
	
	protected void addVariable(String varName, String varContent){
		valueMap.put(varName, varContent);
	}
	
	protected String getVariable(String varName){
		if (valueMap.containsKey(varName))
			return valueMap.get(varName);
		return "";
	}
	
	protected void initializeIterator(){
		iterator = valueMap.keySet().iterator();
	}
	
	protected boolean hasNext(){
		return iterator.hasNext();
	}
	
	protected String getNextElement() {
		if (!iterator.hasNext())
			return "";
		return (String) iterator.next();
	}
	
}
