package a;
import java.util.ArrayList;

public class Component {

	private int identifier;
	protected int time;
	protected Signal[] inputs;
	protected Signal[] outputs;
	
	public Component(){
		identifier = -1;
		inputs = new Signal[1];
		outputs = new Signal[1];
	}
	
	public Component(int id, int inputNum, int outputNum){
		identifier = id;
		inputs = new Signal[inputNum];
		outputs = new Signal[outputNum];
	}
	
	
	public void update(int time){}
	
	public String toString(){
		
		String a = "Inputs:";
		for(Signal in: inputs){
			a += in + " ";
		}
		a += "\nOutputs:";
		for (Signal out : outputs){
			a += out + " ";
		}
		a += "\n";
		
		return a;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public Signal[] getInputs() {
		return inputs;
	}

	public void setInputs(Signal[] inputs) {
		this.inputs = inputs;
	}

	public Signal[] getOutputs() {
		return outputs;
	}

	public void setOutputs(Signal[] outputs) {
		this.outputs = outputs;
	}
	
	public String getName() {
		return null;
	}

	public void connectInput(int location, Signal signal) {
		
		
	}
	
	public void runLogic(){
		
	}
	
	public int getOutputNum(){
		return outputs.length;
	}

	
	
}
