package Board;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Welcome {
	public Welcome() {  
		JFrame f = new JFrame("PvZ");
		JButton b = new JButton("Welcome to our game");
		b.setBounds(100,55,200,40);
		b.addActionListener( new ActionListener(){
	public void actionPerformed(ActionEvent e) {
		
			}
		});
		f.add(b);
		f.setSize(400,200);
		f.setLayout(null);  
		f.setVisible(true);  
	}
}
