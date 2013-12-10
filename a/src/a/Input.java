package a;

public class Input extends Port{
	protected Output connection;
	
	public Input(){
		super();
		connection = new Output();
	}
	
	public Input(Port p) throws WrongPortTypeException{
		if (p instanceof Input){
			throw new WrongPortTypeException();
		}
		else{
			connection = (Output) p;
		}
	}
	
	public void connect(Port p) throws WrongPortTypeException{
		if (p instanceof Input){
			throw new WrongPortTypeException();
		}
		else{
			connection = (Output) p;
		}
	}

	
}
