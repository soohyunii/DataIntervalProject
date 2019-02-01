package grizzly.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;

import grizzly.dao.DBConnection;

public class JPanel02 extends JPanel {
	private JPanelNumber win;
	private JButton buttonStart, buttonRemove, buttonDBInput;
		
	public JPanel02(JPanelNumber win) {
		this.win=win;
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
        buttonStart = new JButton("유격조사 시작");
		buttonRemove = new JButton("원본에서 유격삭제");
		buttonDBInput = new JButton("DB 정보 입력");
		
		frame.add(panel, BorderLayout.CENTER);
		buttonStart.setPreferredSize(new Dimension(200,40));
		frame.add(buttonStart,BorderLayout.NORTH);
		buttonRemove.setPreferredSize(new Dimension(200,40));
		frame.add(buttonRemove, BorderLayout.CENTER);
		buttonDBInput.setPreferredSize(new Dimension(200,40));
		frame.add(buttonDBInput, BorderLayout.WEST);
		
		add(buttonStart);
		add(buttonRemove);
		add(buttonDBInput);
		
		buttonStart.addActionListener(new MyActionListener());
		
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			win.change("jpanel03");
		}
	}

}


