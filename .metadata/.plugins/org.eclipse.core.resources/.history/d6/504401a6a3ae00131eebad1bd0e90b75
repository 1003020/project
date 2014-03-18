package UI;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import a.ComponentStorage;
import a.Component;

public class CircuitDraw extends Frame{
	
	private ComponentStorage c;
	
	public CircuitDraw(int x, int y, ComponentStorage c){
		super();
		this.c = c;
		createUI(x,y);
	}

	private void createUI(int x, int y) {
		
		setSize(x,y);
		
	}
	
	public void draw(ComponentStorage c, Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		for (Component comp : c.getComponents().values()){
			comp.draw(g2);
		}
		
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		for(Component comp : c.getComponents().values()){
			comp.draw(g2);
		}
	}
	
	
}
