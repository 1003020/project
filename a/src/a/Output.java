package a;

public class Output extends Port{
	protected Input connection;
	
	public Output(){
		super();
		connection = null;
	}
	
	public Output(Port p) throws WrongPortTypeException{
		if (p instanceof Output){
			throw new WrongPortTypeException();
		}
		else{
			connection = (Input) p;
		}
	}
	
	public void connect(Port p) throws WrongPortTypeException{
		if (p instanceof Output){
			throw new WrongPortTypeException();
		}
		else{
			connection = (Input) p;
		}
	}
	
	
	
}
