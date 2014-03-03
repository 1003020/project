package a;


import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Wire extends Component{
	private ArrayList<Point2D.Double> path;
	String name;
	
	public Wire(int id){
		super(id, 1, 1);
		name = "Wire";
		outputs[0] = new Signal();
		inputs[0] = null;
		path = new ArrayList<Point2D.Double>();
	}
	
	public Wire(int id, ArrayList<Point2D.Double> path){
		super(id, 1, 1);
		name = "Wire";
		this.path = path;
	}
	
	public void update(int time){
		outputs[0].setBinaryValue(inputs[0].isBinaryValue()); //set output equal to input
		for(Signal s : outputs){
			s.update(time);
		}
	}
	
	public void setup(int i){
		outputs[0].setup(i);
	}
	
	public void propagate(){
		outputs[0].setBinaryValue(inputs[0].isBinaryValue());
		for(Signal s : outputs){
			s.propagate();
		}
	}
	
	public void pulse(){
		this.update(this.time);
	}

	
	public void connectInput(int i, Signal s){
		if(inputs[0] == null){
			inputs[0] = s;
			s.connectOutput(this);
			
		}
		else{
			System.out.println("input already present");
		}
	}
	
	public void addOutput(Component c){
		outputs[0].connectOutput(c);
	}
	
	public void disconnectOutput(int index){
		outputs[0].disconnectOutput(index);
	}
	
	public void disconnectOutput(Component c){
		outputs[0].disconnectOutput(c);
	}
	
	public String getName(){
		return name;
	}
	

	public ArrayList<Point2D.Double> getPath() {
		return path;
	}
	

	public void setPath(ArrayList<Point2D.Double> path) {
		this.path = path;
	}
	
	
}
