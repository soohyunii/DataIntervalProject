package grizzly.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JPanelNumber extends JFrame{
	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	public JPanel03 jpanel03 = null;
	public JPanel04 jpanel04 = null;
	
	public void change(String panelName) {
		if(panelName.equals("jpanel01")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		} else if(panelName.equals("jpanel02")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		} else if(panelName.equals("jpanel03")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel03);
			revalidate();
			repaint();
		} else if(panelName.equals("jpanel04")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel04);
			revalidate();
			repaint();
		}
	}

	public static void main(String[] args) {
		
		JPanelNumber win = new JPanelNumber();
		
		win.setTitle("유격조사 프로그램");
		win.setBounds(800,300,380,350);
		
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);
		win.jpanel03 = new JPanel03(win);
		win.jpanel04 = new JPanel04(win);
		
		win.add(win.jpanel01);
		
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setVisible(true);
	}

}
