package a;

import java.util.ArrayList;
import java.util.HashMap;

public class ComponentStorage {
	
	private ArrayList<Integer> unusedNumbers;
	private HashMap<Integer, Component> components;
	private int time;
	
	public ComponentStorage(int size){
		time = 0;
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
	
	public void setup(){
		for(Component c : components.values()){
			if(c.getName().equals("In")){
				c.setup(0);
			}
		}
		for(Component c : components.values()){
			if(c.getTime() == -1 && !c.getName().equals("Wire")){
				c.feedBackSetup();
			}
		}
		for(Component c : components.values()){
			if(c.getTime() == -1 && c.getName().equals("Wire")){
				c.feedBackSetup();
			}
		}
	}
	
	public void update(){
		
			Boolean updated = false;
			for(Component c : components.values()){
				if(c.getTime()== time){
					updated = true;
					c.propagate();
				}
			}
			time++;
			if(!updated){
				for(Component c : components.values()){
					if(c.getTime()== -2){
						((Gate)c).rundff();
						
					}
				}
				reset();
			}
			
		
	}
	
	public void reset(){
		time = 0;
	}
	
	public void simulate(){
		reset();
		Boolean updated = true;
		while(updated){
			updated = false;
			for(Component c : components.values()){
				if(c.getTime()== time){
					updated = true;
					c.propagate();
				}
			}
			time++;
		}
		for(Component c : components.values()){
			if(c.getTime()== -2){
				((Gate) c).rundff();
				
			}
		}
	}
	
	
}
