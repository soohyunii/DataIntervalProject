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
		System.out.print("접속이름(DB명) :  "+userName);
		String password = jpanel01.password;
		System.out.print("비밀번호 :  "+password);
		String host = jpanel01.host;
		System.out.print("호스트이름 :  "+host);
		String port = jpanel01.port;
		System.out.print("포트번호 :  "+port);
		String sid = jpanel01.sid;
		System.out.print("SID :  "+sid);
		
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
			return conn;			
	}

}
