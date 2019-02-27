package grizzly.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import grizzly.view.JPanel01;
import grizzly.view.JPanelNumber;

public class AfterDBConnection {
	public static Connection dbConn;
	public static Connection getConnection()
	{
		JPanelNumber win = new JPanelNumber();
		JPanel01 jpanel01 = new JPanel01(win);
		Connection conn = null;
		Scanner scan = new Scanner(System.in);
		
		String userName = jpanel01.userName;
		System.out.print("�����̸�(DB��) :  "+userName);
		String password = jpanel01.password;
		System.out.print("��й�ȣ :  "+password);
		String host = jpanel01.host;
		System.out.print("ȣ��Ʈ�̸� :  "+host);
		String port = jpanel01.port;
		System.out.print("��Ʈ��ȣ :  "+port);
		String sid = jpanel01.sid;
		System.out.print("SID :  "+sid);
		
			try {
				String user=userName;
				String pw=password;
				String url="jdbc:oracle:thin:@"+host+":"+port+":"+sid;
				
				// JDBC ����̹�(ojdbc6.jar)�� �ε��ϴ� �κ�
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url,user,pw);
				
				System.out.println("Database�� ����Ǿ����ϴ�.\n");
				System.out.println("==============================");		
				
			} catch(ClassNotFoundException cnfe) {
				System.out.println("DB����̹� �ε� ���� : "+cnfe.toString());
			} catch(SQLException sqle) {
				System.out.println("DB ���ӽ��� : "+sqle.toString());
			} catch(Exception e) {
				System.out.println("Unknown error");
				e.printStackTrace();
			}
			return conn;			
	}

}
