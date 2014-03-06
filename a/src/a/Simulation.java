package a;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import Parser.FileRead;
import UI.Display;

public class Simulation {

	public static void main(String[] args) {
		Display d = null;
		Scanner inputScanner = new Scanner(System.in);
		ArrayList<Component> gatesInUse = new ArrayList<Component>();
		ComponentStorage gates = new ComponentStorage(100);
		int id;
		int location;
		String input;
		int id2;
		int location2;
		int count = 0;
		boolean updated;


		HashMap<String, Gate> gateTypes = FileRead.readComponentTypes();

		System.out.println("Gates available: ");
		for (String s :gateTypes.keySet()){
			System.out.println(s + " : " + ((Gate) gateTypes.get(s)).getName());
		}


		while (true){
			input = inputScanner.next();
			if(input.equals("exit")){
				inputScanner.close();
				break
				;
			}
			else if(input.equals("add")){
				input = inputScanner.next();
				location = gates.addComponent(new Gate((Gate) gateTypes.get(input)));
				System.out.println("Gate " + ((Gate) gateTypes.get(input)).getName() + " added with key " + location);
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

			else if(input.equals("setTime")){
				gates.getComponent(inputScanner.nextInt()).setTime(inputScanner.nextInt());
			}
			else if(input.equals("getTime")){
				System.out.println(gates.getComponent(inputScanner.nextInt()).getTime());
			}

			else if(input.equals("simulate")){
				count = 0;
				updated = true;
				while(updated){
					updated = false;
					for(Component c : gates.getComponents().values()){
						if(c.getTime()== count){
							updated = true;
							c.propagate();
						}
					}
					count++;
				}

			}
			else if(input.equals("propagate")){
				updated = false;
				for(Component c : gates.getComponents().values()){
					if(c.getTime()== count){
						updated = true;
						c.propagate();
					}
				}
				count++;
				if(!updated){
					for(Component c : gates.getComponents().values()){
						if(c.getTime()== -2){
							c.propagate();
						}
					}
				}
				if(d != null){
					d.repaint();
				}
				
			}

			else if(input.equals("load")){
				input = inputScanner.next();
				gates = FileRead.loadCircuit(input, gateTypes);
				
				for(Component c : gates.getComponents().values()){
					
				}
				for(Component c : gates.getComponents().values()){
					if(c.getName().equals("In")){
						c.setup(0);
					}
				}
				d = new Display(800,800, gates);
				d.setVisible(true);
			}
			
			else if(input.equals("reset")){
				count = 0;
			}
			
			

		}
	}

}
