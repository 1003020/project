package UI;

import java.awt.*;
import java.awt.event.*;


import a.ComponentStorage;
import a.Component;

//Based on the ApplicationFrame class detailed in "Java 2d Graphics" by Jonathan Knudsen
public class Display extends Frame implements ActionListener{
	private ComponentStorage c;
	
	public Display(int x, int y, ComponentStorage c){
		super();
		this.c = c;
		createUI(x,y);
	}
	
	public void createUI(int x, int y){
		
		setSize(x,y);
		center();
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	public void center(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width)/2;
		int y = (screenSize.height - frameSize.height)/2;
		setLocation(x,y);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		c.update();
		
	}
}
