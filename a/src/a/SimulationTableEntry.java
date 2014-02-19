package a;

import java.util.ArrayList;

public class SimulationTableEntry {
	private ArrayList<Component> inputs;
	private ArrayList<Component> outputs;
	
	public SimulationTableEntry(ComponentStorage c){
		for (int i : c.getComponents().keySet()){
			if (c.getComponent(i).getName().equals("input")){
				inputs.add(c.getComponent(i));
			}
			else if (c.getComponent(i).getName().equals("output")){
				outputs.add(c.getComponent(i));
			}
		}
	}
}
