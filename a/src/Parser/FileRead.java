package Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.regex.*;
import java.util.HashMap;

import a.Component;
import a.ComponentStorage;
import a.Gate;
import a.Signal;
import a.Wire;

public class FileRead {
	
	public static HashMap<String, Gate> readComponentTypes(){
		HashMap<String, Gate> gates = new HashMap<String, Gate>();
		ArrayList<Gate> gateTypes = new ArrayList<Gate>();
		Gate g = null;
		Pattern p = Pattern.compile(".*~");
		File gatesfolder = new File("./Gates/");
		File[] gateList = gatesfolder.listFiles();
		for (File f : gateList){
			if(f.isFile() && !f.isHidden()){ //If the file isn't a directory or hidden
				if(!(p.matcher(f.getName())).matches()){ //If it doesn't end with a ~
					g = readGate(f);
					gateTypes.add(g);
					gates.put(g.getName(), g);
				}
				
			}
		}
		return gates;
	}
	
	private static Gate readGate(File f){
		
		Scanner s = null;
		try {
			s = new Scanner(new FileInputStream(f));
		} catch (FileNotFoundException e) {
		}
		
		String name = s.next();
		int inputNum = s.nextInt();
		int outputNum = s.nextInt();
		Double x = s.nextDouble();
		Double y = s.nextDouble();
		Point2D.Double dimensions = new Point2D.Double(x,y);
		Stack[] logic = new Stack[outputNum];
		
		for(int i = 0; i<outputNum; i++){
			logic[i] = new Stack<String>();
		}
		
		String current;
		
		for (int i = 0; i< outputNum; i++){
			while(!(current = s.next()).equals("end")){
				logic[i].addElement(current);
			}
		}
		
		Gate g = new Gate(name, 1, inputNum, outputNum, new Point2D.Double(0,0), dimensions, logic);
		
		s.close();
		return g;
	}
	
	public static ComponentStorage loadCircuit(String fileName, HashMap<String, Gate> gates){
		ComponentStorage c = new ComponentStorage(100);
		File circuit = new File("./Circuits/" + fileName);
		Scanner s = null;
		try{
			s = new Scanner(new FileInputStream(circuit));
		} catch (FileNotFoundException e) {
		}
		
		int id;
		String name;
		Component g;
		ArrayList<Point2D.Double> path = new ArrayList<Point2D.Double>();
		
		while(s.hasNext()){
			id = s.nextInt();
			name = s.next();
			if (name.equals("Wire")){
				g = new Wire(id);
				path = new ArrayList<Point2D.Double>();
				while(true){
					if(s.next().equals("point")){
						path.add(new Point2D.Double(s.nextDouble(),s.nextDouble()));
					}
					else{
						break;
					}
					
				}
				g.setPath(path);
			}
			else{
				g = new Gate(gates.get(name));
				g.setPoint(new Point2D.Double(s.nextDouble(),s.nextDouble()));
			}
			
			c.addComponent(g, id);
			s.nextLine();
		}
		s.close();
		
		try{
			s = new Scanner(new FileInputStream(circuit));
		} catch (FileNotFoundException e) {
		}
		while(s.hasNext()){
			id = s.nextInt();
			
			g = c.getComponent(id);
			if(g.getName().equals("Wire")){
				while(!s.next().equals("end")){
					
				}
			}
			else{
				s.next();
				s.nextDouble(); s.nextDouble();
			}
			for(int i = 0; i < g.getInputNum(); i++){
					id = s.nextInt();
					g.connectInput(i, c.getComponent(id).getOutputs()[s.nextInt()-1]);
			}
			
		}
		

		
		
		s.close();
		return c;
	}
	
	public static void save(ComponentStorage c, String fileName){
		Component g = null;
		PrintWriter s = null;
		try{
			s = new PrintWriter(new FileOutputStream("./Circuits/" + fileName + ".txt"));
		}catch(Exception e){
		}
		
		Integer[] numbers = (Integer[]) c.getComponents().keySet().toArray();
		for (Integer i : numbers){
			g = c.getComponent(i);
			s.write(i + " ");
			s.write(g.getName() + " ");
			for(Signal signal : g.getOutputs()){
				for(Component component : signal.getOutputs()){
					s.write(component.getIdentifier() + " ");
				}
				s.write("end");
			}
		}
		
		
	}
	
}



