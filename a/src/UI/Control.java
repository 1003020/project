package UI;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JToolBar;

import a.ComponentStorage;

public class Control extends Frame implements ActionListener{
	private ComponentStorage c;
	Display d;
	
	public Control(ComponentStorage c, Display d){
		super("control bar");
		this.c = c;
		this.d = d;
		
		createUI();
	}
	
	public void createUI(){
		setSize(100, 50);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		JToolBar toolBar = new JToolBar();
		JButton button = makeButton("propagate", "prop", "propagate the signal");
		toolBar.add(button);
		button = makeButton("simulate", "sim", "simulate until all outputs are stable");
		toolBar.add(button);
		this.add(toolBar);
		this.pack();
		
	}
	
	public JButton makeButton(String action, String text, String alt){
		JButton button = new JButton();
		
		button.setActionCommand(action);;
		button.setText(text);
		button.setToolTipText(alt);
		button.addActionListener(this);
		
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
 
        // Handle each button.
        if (cmd.equals("propagate")) { //first button clicked
            c.update();
            d.repaint();
        } else if (cmd.equals("simulate")) { // second button clicked
            c.simulate();
            d.repaint();
        }
		
	}
	
}
