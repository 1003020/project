package a;

public class Input extends Port{
	
	public Input(Port p) throws WrongPortTypeException{
		if (p instanceof Input){
			throw new WrongPortTypeException();
		}
		else{
			connection = p;
		}
	}
	
	public void connect(Port p) throws WrongPortTypeException{
		if (p instanceof Input){
			throw new WrongPortTypeException();
		}
		else{
			connection = p;
		}
	}

	
}
