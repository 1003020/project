package a;

public abstract class Port {
	
	public Port(){
		
	}
	
	public abstract void connect(Port p) throws WrongPortTypeException;
}
