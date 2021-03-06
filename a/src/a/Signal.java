package a;
import java.util.ArrayList;

public class Signal {
	private int identifier;
	private Component input;
	private ArrayList<Component> outputs;
	private boolean binaryValue;
	
	public Signal(){
		identifier = 0;
		input = null;
		outputs = new ArrayList<Component>();
		binaryValue = false;
	}
	
	public Signal(Component c){
		identifier = 0;
		input = c;
		outputs = new ArrayList<Component>();
		binaryValue = false;
	}
	
	public void update(int time){
		for (Component c : outputs){
			c.update(time);
		}
	}
	
	public void propagate(){
		for (Component c : outputs){
			if(c.getName().equals("Wire")){
				c.propagate();
			}
		}
	}
	
	public void setup(int i){
		for (Component c : outputs){
			c.setup(i);
		}
	}
	
	public void connectInput(Component c){
		input = c;
	}
	
	public void connectOutput(Component c){
		outputs.add(c);
	}
	
	public boolean noInput(){
		return input == null;
	}
	
	
	public void disconnectInput(){
		input = null;
	}
	
	public void disconnectOutputs(){
		outputs = new ArrayList<Component>();
	}
	
	public void disconnectOutput(int index){
		outputs.remove(index);
	}
	
	public void disconnectOutput(Component c){
		
		outputs.remove(c);
	}

	

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public Component getInput() {
		return input;
	}

	public void setInput(Component input) {
		this.input = input;
	}

	public ArrayList<Component> getOutputs() {
		return outputs;
	}

	public void setOutputs(ArrayList<Component> outputs) {
		this.outputs = outputs;
	}

	public boolean isBinaryValue() {
		return binaryValue;
	}

	public void setBinaryValue(boolean binaryValue) {
		this.binaryValue = binaryValue;
	}
	
	
}
