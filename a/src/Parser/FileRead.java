package Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.*;

import a.Component;
import a.Gate;
import a.Signal;

public class FileRead {
	
	public static ArrayList<Gate> readComponentTypes(){
		ArrayList<Gate> gateTypes = new ArrayList<Gate>();
		Pattern p = Pattern.compile(".*~");
		Matcher m;
		File gatesfolder = new File("./Gates/");
		File[] gateList = gatesfolder.listFiles();
		for (File f : gateList){
			if(f.isFile() && !f.isHidden()){
				if(!(m = p.matcher(f.getName())).matches()){
					gateTypes.add(readGate(f));
				}
				
			}
		}
		return gateTypes;
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
}