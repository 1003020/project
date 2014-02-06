package a;

import java.util.ArrayList;
import java.util.HashMap;

public class ComponentStorage {
	
	private ArrayList<Integer> unusedNumbers;
	private HashMap<Integer, Component> components;
	
	public ComponentStorage(int size){
		unusedNumbers = new ArrayList<Integer>();
		components = new HashMap<Integer, Component>(size);
		for(Integer i = 1; i<=size; i++){
			unusedNumbers.add(i);
		}
	}
	
	public Integer addComponent(Component c){
		Integer id = unusedNumbers.get(0);
		unusedNumbers.remove(0);
		c.setIdentifier(id);
		components.put(id, c);
		return id;
	}
	
	public Integer addComponent(Component c, Integer k){
		components.put(k, c);
		unusedNumbers.remove(k);
		c.setIdentifier(k);
		return k;
	}
	
	
	public Component getComponent(Integer k){
		return components.get(k);
	}
	
	public HashMap<Integer, Component> getComponents(){
		return components;
	}
	
	public ArrayList<Integer> getNumbersInUse(){
		return unusedNumbers;
	}
}
