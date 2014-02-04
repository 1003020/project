package a;
import java.util.ArrayList;

public class Signal {
	private int identifier;
	private Component input;
	private ArrayList<Component> outputs;
	private boolean binaryValue;
	
	public Signal(){
		input = null;
		outputs = new ArrayList<Component>();
		binaryValue = false;
	}
	
	public void update(){
		for (Component c : outputs){
			c.update();
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