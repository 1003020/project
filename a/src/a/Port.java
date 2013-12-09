package a;

public abstract class Port {
	
	protected Port connection;
	
	public abstract void connect(Port p) throws WrongPortTypeException;
}
