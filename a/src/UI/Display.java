package UI;

import java.awt.*;
import java.awt.event.*;

//Based on the ApplicationFrame class detailed in "Java 2d Graphics" by Jonathan Knudsen
public class Display extends Frame{
	
	
	public Display(int x, int y){
		super();
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
}
