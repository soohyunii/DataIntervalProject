package grizzly.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.*;

import grizzly.dao.DBConnection;

public class JPanel01 extends JPanel{
	private JButton jBAddRow=null;
	private DBConnection dbConn;
	private JTextField DBtextField, PWtextField, HostTextField, PortTextField, SidTextField;
	public String userName,password,host,port,sid;
	private JPanelNumber win;
	
	DBConnection dbc = new DBConnection(); 
	JButton buttonInput=new JButton("�Է�");
	
	public JPanel01(JPanelNumber win) {
		this.win=win;
		
		DBtextField = new JTextField(30);
		PWtextField = new JTextField(30);
		HostTextField = new JTextField(30);
		PortTextField = new JTextField(30);
		SidTextField = new JTextField(30);
		
		
		JLabel labelDB = new JLabel("�����̸�(DB��) : ");
		JLabel labelPW = new JLabel("��й�ȣ : ");
		JLabel labelHost = new JLabel("ȣ��Ʈ�̸� : ");
		JLabel labelPort = new JLabel("��Ʈ��ȣ : ");
		JLabel labelSid = new JLabel("SID : ");
			
		add(labelDB);
		add(DBtextField);
		add(labelPW);
		add(PWtextField);
		add(labelHost);
		add(HostTextField);
		add(labelPort);
		add(PortTextField);
		add(labelSid);
		add(SidTextField);
		
		add(buttonInput);
		
		setVisible(true);
		
		buttonInput.addActionListener(new MyActionListener());
	  }
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			// win.change("panel01");
				if(evt.getSource()==buttonInput) {
					userName=String.valueOf(DBtextField.getText());
					password=String.valueOf(PWtextField.getText());
					host=String.valueOf(HostTextField.getText());
					port=String.valueOf(PortTextField.getText());
					sid=String.valueOf(SidTextField.getText());
					
					Connection conn = null; 
					
					try {
						String user=userName;
						String pw=password;
						String url="jdbc:oracle:thin:@"+host+":"+port+":"+sid;
						
						// JDBC ����̹�(ojdbc6.jar)�� �ε��ϴ� �κ�
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn = DriverManager.getConnection(url,user,pw);
						
						System.out.println("Database�� ����Ǿ����ϴ�.\n");
						System.out.println("==============================");
						JOptionPane.showMessageDialog(buttonInput, "Database�� ����Ǿ����ϴ�");
						win.change("jpanel02");
						
					} catch(ClassNotFoundException cnfe) {
						JOptionPane.showMessageDialog(buttonInput,"DB����̹� �ε� ���� : "+cnfe.toString());
					} catch(SQLException sqle) {
						JOptionPane.showMessageDialog(buttonInput,"DB ���ӽ��� : "+sqle.toString());
					} catch(Exception e) {
						System.out.println("Unknown error");
						JOptionPane.showMessageDialog(buttonInput,"�� �� ���� ������ �߻��߽��ϴ�");
						e.printStackTrace();
					}
					return;

				}	
			
		}
	  }
	}