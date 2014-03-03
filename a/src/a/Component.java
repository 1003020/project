package a;

import java.awt.geom.Point2D;


public class Component {

	protected int identifier;
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
		time = -1;
	}
	
	
	public void update(int i){
		
	}
	
	public void propagate(){
		
	}
	
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
	
	public void setPoint(Point2D.Double d){
		
	}
	
	public void pulse(){
		
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
	
	public int getInputNum(){
		return inputs.length;
	}
	
	public int getTime(){
		return time;
	}
	public void setTime(int time){
		this.time = time;
	}

	public void setup(int i) {
		// TODO Auto-generated method stub
		
	}
	


	
	
}
