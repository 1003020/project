package a;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.ArrayList;

public class Wire extends Component{
	private ArrayList<Point2D.Double> path;
	String name;
	
	public Wire(int id){
		super(id, 1, 1);
		name = "Wire";
		outputs[0] = new Signal(this);
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
		if(inputs[0].getInput().getTime() != -1){
			this.time = i;
			outputs[0].setup(i);
		}
	}
	
	public void feedBackSetup(){
		this.time = inputs[0].getInput().getTime();
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
	
	public void draw(Graphics2D g){
		if(inputs[0].isBinaryValue()){
			g.setColor(Color.RED);
		}
		else{
			g.setColor(Color.BLACK);
		}
		for(int i = 1; i< path.size(); i++){
			g.drawLine((int)path.get(i-1).x, (int)path.get(i-1).y, (int)path.get(i).x, (int)path.get(i).y);
		}
		g.setColor(Color.BLACK);
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
