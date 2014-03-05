package a;

import java.awt.geom.Point2D;
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
			outputs[i] = new Signal();
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
			
			outputs[0].setup(0);
			
		}
		else if(this.name.equals("Out")){
			if(time != 0){
				time = i + 1;
			}

		}
		else if(this.name.equals("dff")){
			this.time = -2;
		}
		else{
			for (Signal s : inputs){
				if(s.getInput().getTime() == -1){
					b = false;
				}
			}
			if (this.time == 0 && b){
				time = i + 1;
				for(Signal s : outputs){
					s.setup(this.time);
				}
			}
		}
		
	}
	
	public void propagate(){
		this.runLogic();
		for(Signal s : outputs){
			s.propagate();
		}
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
