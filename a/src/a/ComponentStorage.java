package a;

import java.util.ArrayList;

public class ComponentStorage {
	
	private ArrayList<ComponentStorageUnit> components;
	
	public ComponentStorage(){
		components = new ArrayList<ComponentStorageUnit>();
	}
	
	public int add(ComponentStorageUnit c){
		int index = components.size();
		components.add(c);
		return index;
	}
	
}
