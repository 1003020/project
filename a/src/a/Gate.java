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
	
	public Gate(String name, int id, int inputNum, int outputNum, Point2D.Double topLeft, Point2D.Double bottomRight, Stack[] logic){
		super(id, inputNum, outputNum);
		this.name = name;
		for (int i = 0; i<outputNum; i++){
			outputs[i] = new Signal();
		}
		this.logic = logic;
		this.inputNum = inputNum;
		this.outputNum = outputNum;
		topLeftCorner = topLeft;
		bottomRightCorner = bottomRight;
	}
	
	public Gate(Gate g){
		super(g.getIdentifier(), g.getInputNum(), g.getOutputNum());
		this.name = g.getName();
		this.inputNum = g.getInputNum();
		this.outputNum = g.getOutputNum();
		for (int i = 0; i<outputNum; i++){
			outputs[i] = new Signal();
		}
		this.logic = g.getLogic();
		this.topLeftCorner = g.getTopLeftCorner();
		this.bottomRightCorner = g.getBottomRightCorner();
		
	}
	
	public void update(int time){
		this.time = time+1;
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
				else{
					index = Integer.parseInt(current);
					index--;
					logicBlock[i].push(inputs[index].isBinaryValue());
				}
			}
		}
		for(int i = 0; i < outputNum; i++){
			System.out.println("setting output " + i);
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
	
	public int getTime(){
		return time;
	}
	public void setTime(int i){
		this.time = i;
	}
	
	
}
