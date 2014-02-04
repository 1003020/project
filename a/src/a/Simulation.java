package a;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Parser.FileRead;;

public class Simulation {

	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		ArrayList<Component> gatesInUse = new ArrayList<Component>();
		ComponentStorage gates = new ComponentStorage();
		int id;
		int location;
		String input;
		int id2;
		int location2;
		
		
		ArrayList<Gate> gateTypes = FileRead.readComponentTypes();
		
		System.out.println("Gates available: ");
		for (int i = 0; i< gateTypes.size(); i++){
			System.out.println(i + " : " + ((Gate) gateTypes.get(i)).getName());
		}
		
		
		while (true){
			input = inputScanner.next();
			if(input.equals("exit")){
				inputScanner.close();
				break;
			}
			else if(input.equals("add")){
				id = inputScanner.nextInt();
				location = gates.addComponent(new Gate((Gate) gateTypes.get(id)));
				System.out.println("Gate " + ((Gate) gateTypes.get(id)).getName() + " added with key " + location);
			}
			else if (input.equals("addwire")){
				location = gates.addComponent(new Wire(1));
				System.out.println("Wire added with key " + location);
			}
			else if (input.equals("get")){
				id = inputScanner.nextInt();
				System.out.println("Component " + gates.getComponent(id).getName() + " with key " + id);
			}
			else if(input.equals("connectInput")){
				id = inputScanner.nextInt();
				location = inputScanner.nextInt();
				id2 = inputScanner.nextInt();
				location2 = inputScanner.nextInt();
				//connect input of first gate to output of second
				gates.getComponent(id).connectInput(location-1, gates.getComponent(id2).getOutputs()[location2-1]);
			}
			
			
			else if(input.equals("setout")){
				id = inputScanner.nextInt();
				location = inputScanner.nextInt();
				input = inputScanner.next();
				if(input.equals("true")){
					gates.getComponent(id).getOutputs()[location-1].setBinaryValue(true);
				}
				else{
					gates.getComponent(id).getOutputs()[location-1].setBinaryValue(false);
				}
			}
			
			
			
			else if(input.equals("runlogic")){
				id = inputScanner.nextInt();
				gates.getComponent(id).runLogic();
				
			}
			
			
			else if (input.equals("getout")){
				id = inputScanner.nextInt();
				location = inputScanner.nextInt();
				System.out.println(gates.getComponent(id).getOutputs()[location-1].isBinaryValue());
			}
			else if(input.equals("getin")){
				id = inputScanner.nextInt();
				location = inputScanner.nextInt();
				System.out.println(gates.getComponent(id).getInputs()[location].isBinaryValue());
			}
					
		}
	}

}