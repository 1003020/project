package a;
import java.util.ArrayList;

public class Component {
	private ArrayList<Port> inputs;
	private ArrayList<Port> outputs;
	
	public Component(Component c){
		this.inputs = c.getInputs();
		this.outputs = c.getOutputs();
	}
	
	
	public void update(){}
	
	public String toString(){
		
		String a = "Inputs:";
		for(Port in: inputs){
			a += in + " ";
		}
		a += "\nOutputs:";
		for (Port out : outputs){
			a += out + " ";
		}
		a += "\n";
		
		return a;
	}


	public ArrayList<Port> getInputs() {
		return inputs;
	}


	public void setInputs(ArrayList<Port> inputs) {
		this.inputs = inputs;
	}


	public ArrayList<Port> getOutputs() {
		return outputs;
	}


	public void setOutputs(ArrayList<Port> outputs) {
		this.outputs = outputs;
	}
	
	
	
}
