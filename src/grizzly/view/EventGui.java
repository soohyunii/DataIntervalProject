package grizzly.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import grizzly.dao.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EventGui extends JFrame implements ActionListener{	
	private JButton jBAddRow=null;
	private DBConnection dbConn;
	private JTextField DBtextField, PWtextField, HostTextField, PortTextField, SidTextField;
	String userName,password,host,port,sid;
	
	//DB접속 생성자
	DBConnection dbc = new DBConnection(); 
	JButton buttonInput=new JButton("입력");

	public EventGui() {
		super("Event Start");
		
		setBounds(800,300,380,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane=this.getContentPane();
		
		JPanel pane=new JPanel();
		
		JTextField DBtextField = new JTextField(30);
		JTextField PWtextField = new JTextField(30);
		JTextField HostTextField = new JTextField(30);
		JTextField PortTextField = new JTextField(30);
		JTextField SidTextField = new JTextField(30);
		JLabel labelDB = new JLabel("접속이름(DB명) : ");
		JLabel labelPW = new JLabel("비밀번호 : ");
		JLabel labelHost = new JLabel("호스트이름 : ");
		JLabel labelPort = new JLabel("포트번호 : ");
		JLabel labelSid = new JLabel("SID : ");
		
		
		buttonInput.setMnemonic('S');     // 단축키설정
			
		pane.add(labelDB);
		pane.add(DBtextField);
		pane.add(labelPW);
		pane.add(PWtextField);
		pane.add(labelHost);
		pane.add(HostTextField);
		pane.add(labelPort);
		pane.add(PortTextField);
		pane.add(labelSid);
		pane.add(SidTextField);
		
		pane.add(buttonInput);
		contentPane.add(pane);
		
		setVisible(true);
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// 입력버튼을 클릭했을 경우 발생하는 event method
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
						
						// JDBC 드라이버(ojdbc6.jar)를 로딩하는 부분
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn = DriverManager.getConnection(url,user,pw);
						
						System.out.println("Database에 연결되었습니다.\n");
						System.out.println("==============================");	
						
						
					} catch(ClassNotFoundException cnfe) {
						System.out.println("DB드라이버 로딩 실패 : "+cnfe.toString());
					} catch(SQLException sqle) {
						System.out.println("DB 접속실패 : "+sqle.toString());
					} catch(Exception e) {
						System.out.println("Unknown error");
						e.printStackTrace();
					}
					return;

				}
				
			}
			
		};
		
		buttonInput.addActionListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
	}
}
