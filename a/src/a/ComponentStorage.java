package a;

import java.util.ArrayList;
import java.util.HashMap;

public class ComponentStorage {
	
	private ArrayList<Integer> numbersInUse;
	private HashMap<Integer, Component> components;
	
	public ComponentStorage(){
		numbersInUse = new ArrayList<Integer>();
		components = new HashMap<Integer, Component>(100);
	}
	
	public Integer addComponent(Component c){
		components.put(numbersInUse.size()+1, c);
		numbersInUse.add(numbersInUse.size()+1);
		c.setIdentifier(numbersInUse.size());
		return numbersInUse.size();
	}
	
	public Integer addComponent(Component c, Integer k){
		components.put(k, c);
		numbersInUse.add(k);
		c.setIdentifier(k);
		return k;
	}
	
	
	public Component getComponent(Integer k){
		return components.get(k);
	}
}
