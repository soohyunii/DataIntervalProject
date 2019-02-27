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
	public String tableName, tablePk;
	
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
			// JPanel03 jpanel03 = new JPanel03(win);
			if(evt.getSource()==buttonInput) {
				tableName=String.valueOf(TableTextField.getText());
				tablePk=String.valueOf(PKtextField.getText());
				win.change("jpanel04");
				//DB ������ �̹� �Ǿ��ִ��� Ȯ��
				System.out.println("������ ���̺� �� : "+tableName);
				System.out.println("���̺��� PK : "+tablePk);
			}
		}
	}
}
