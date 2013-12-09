package a;
import java.util.ArrayList;

public class Component {
	private ArrayList<Port> inputs;
	private ArrayList<Port> outputs;
	
	public void update(){}
	
	public String toString(){
		
		String a = "Inputs:";
		for(Port in: inputs){
			a += in + " ";
		}
		a += "\nOutputs:";
		for (Port out : outputs){
			a += out + " ";
		}
		a += "\n";
		
		return a;
	}
	
	
	
}
