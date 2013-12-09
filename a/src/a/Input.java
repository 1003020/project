package a;

public class Input extends Port{
	
	public Input(Port p) throws wrongPortTypeException{
		if (p instanceof Input){
			throw wrongPortTypeException;
		}
		else{
			connection = p;
		}
	}
	
	public void connect(Port p) throws wongPortTypeException{
		if (p instanceof Input){
			throw wrongPortTypeException;
		}
		else{
			connection = p;
		}
	}
	
}
