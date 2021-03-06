package a;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.Stack;

public class Gate extends Component{
	private String name;
	private int inputNum;
	private int outputNum;
	private Stack[] logic;
	private Point2D.Double topLeftCorner;
	private Point2D.Double bottomRightCorner;
	
	public Gate(String name, int id, int inputNum, int outputNum, Point2D.Double topLeft, Point2D.Double size, Stack[] logic){
		super(id, inputNum, outputNum);
		this.name = name;
		for (int i = 0; i<outputNum; i++){
			outputs[i] = new Signal(this);
		}
		this.logic = logic;
		this.inputNum = inputNum;
		this.outputNum = outputNum;
		topLeftCorner = topLeft;
		bottomRightCorner = size;
	}
	
	public Gate(Gate g){
		super(g.getIdentifier(), g.getInputNum(), g.getOutputNum());
		this.name = g.getName();
		this.inputNum = g.getInputNum();
		this.outputNum = g.getOutputNum();
		for (int i = 0; i<outputNum; i++){
			outputs[i] = new Signal(this);
		}
		this.logic = g.getLogic();
		this.topLeftCorner = g.getTopLeftCorner();
		this.bottomRightCorner = g.getBottomRightCorner();
		
	}
	
	public void update(int time){
		if(this.time == -1){
			this.time = time+1;
			System.out.println("Gate " + identifier + " of type " + name + " set to time " + this.time);
		}
		
	}
	
	public void setup (int i){
		boolean b = true;
		if(this.name.equals("In")){
			this.time = 0;
			outputs[0].setup(0);
			
		}
		else if(this.name.equals("Out")){
			if(time == -1){
				time = i + 1;
			}

		}
		else if(this.name.equals("dff")){
			this.time = -2;
			for (Signal s : outputs){
				s.setup(i+1);
			}
		}
		else{
			for (Signal s : inputs){
				if(s != null ){
					if(s.getInput().getTime() == -1){
						b = false;
					}
					
				}
			}
			if (this.time == -1 && b){
				int max = 0;
				for(Signal s : inputs){
					if(s.getInput().getTime() > max){
						max = s.getInput().getTime();
					}
				}
				this.time = max + 1;
				for(Signal s : outputs){
					s.setup(this.time);
				}
			}
		}
		
	}
	
	public void feedBackSetup(){
		int max = 0;
		for(Signal s : inputs){
			if(s.getInput().getTime() > max){
				max = s.getInput().getTime();
			}
		}
		this.time = max + 1;
	}
	
	public void propagate(){
		if(name.equals("In")){
			
		}
		else if(name.equals("dff")){
			
		}
		else{
			this.runLogic();
			for(Signal s : outputs){
				s.propagate();
			}
		}
	}
	
	public void rundff(){
		this.runLogic();
		
		
	}
	
	public void pulse(){
		this.runLogic();
		for(Signal s : outputs){
			s.update(this.time);
		}
	}
	
	public void runLogic(){
		String current;
		Stack<String> tempLogic = new Stack<String>();
		Stack<Boolean>[] logicBlock = new Stack[outputNum];
		int index;
		Boolean value;
		Boolean value2;
		//logicBlock[0] = new Stack<Boolean>();
		//logicBlock[0].push(true);
		
		
		for(int i = 0; i < outputNum; i++){
			logicBlock[i] = new Stack<Boolean>();
			tempLogic =  (Stack) logic[i].clone();
			
			while(!tempLogic.isEmpty()){
				current = (String) tempLogic.pop();
				if (current.equals("or2")){
					logicBlock[i].push(logicBlock[i].pop() || logicBlock[i].pop());
				}
				else if(current.equals("and2")){
					logicBlock[i].push(logicBlock[i].pop() && logicBlock[i].pop());
				}
				else if(current.equals("inv")){
					logicBlock[i].push(!logicBlock[i].pop());
				}
				else if(current.equals("xor2")){
					value = logicBlock[i].pop();
					value2 = logicBlock[i].pop();
					logicBlock[i].push((value || value2) && !(value && value2));
				}
				else if(current.equals("nand2")){
					logicBlock[i].push(!(logicBlock[i].pop() && logicBlock[i].pop()));
				}
				else{
					index = Integer.parseInt(current);
					index--;
					logicBlock[i].push(inputs[index].isBinaryValue());
				}
			}
		}
		for(int i = 0; i < outputNum; i++){
			System.out.println("setting output " + i + " of " + name + " gate " + identifier);
			System.out.println(logicBlock[i].peek());
			outputs[i].setBinaryValue(logicBlock[i].peek());
		}
	}
	
	public void draw(Graphics2D g){
		g.draw(new Rectangle2D.Double(topLeftCorner.x,topLeftCorner.y, bottomRightCorner.x, bottomRightCorner.y));
		String info = name + " " + identifier;
		g.drawChars(info.toCharArray(), 0, info.length(), (int) (topLeftCorner.x + (bottomRightCorner.x/2) - 20), 
				(int) (topLeftCorner.y + (bottomRightCorner.y/2)));
	}
	
	
	
	public void connectInput(int index, Signal s){
		if(index > inputs.length){
			System.out.println("No such input");
		}
		else if(inputs[index] != null){
			System.out.println("Input present");
		}
		else{
			inputs[index] = s;
			s.connectOutput(this);
		}
		
	}
	
	public void disconnectInput(int index){
		if(index > inputNum){
			
		}
		else{
			inputs[index] = null;
		}
	}
	
	public void connectOutput(int index, Component c){
		if(index> outputNum -1){
			System.out.println("No such output");
		}
		else{
			outputs[index].connectOutput(c);
		}
	}
	
	public void disconnectOutputs(int index){
		
	}
	
	public void setPoint(Point2D.Double point){
		topLeftCorner = point;
	}
	
	public String getName(){
		return name;
	}

	public int getInputNum() {
		return inputNum;
	}

	public void setInputNum(int inputNum) {
		this.inputNum = inputNum;
	}

	public int getOutputNum() {
		return outputNum;
	}

	public void setOutputNum(int outputNum) {
		this.outputNum = outputNum;
	}

	public Stack[] getLogic() {
		return logic;
	}

	public void setLogic(Stack[] logic) {
		this.logic = logic;
	}

	public Point2D.Double getTopLeftCorner() {
		return topLeftCorner;
	}

	public void setTopLeftCorner(Point2D.Double topLeftCorner) {
		this.topLeftCorner = topLeftCorner;
	}

	public Point2D.Double getBottomRightCorner() {
		return bottomRightCorner;
	}

	public void setBottomRightCorner(Point2D.Double bottomRightCorner) {
		this.bottomRightCorner = bottomRightCorner;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
