package grizzly.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JPanel03 extends JPanel{
	private JPanelNumber win;
	private JTextField TableTextField, PKtextField;
	
	JButton buttonInput = new JButton("�Է�");
	JButton buttonBack = new JButton("�ڷΰ���");
	
	public JPanel03(JPanelNumber win) {
		this.win=win;
		
		TableTextField = new JTextField(30);
		PKtextField = new JTextField(30);
		
		JLabel labelTable = new JLabel("���̺��: ");
		JLabel labelPK = new JLabel("PK: ");
		
		add(labelTable);
		add(TableTextField);
		add(labelPK);
		add(PKtextField);
		
		add(buttonInput);
		add(buttonBack);
		
		buttonInput.addActionListener(new MyActionListener());
	}
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()==buttonInput) {
				win.change("jpanel04");
				//DB ������ �̹� �Ǿ��ִ��� Ȯ��
			}
		}
	}

}
