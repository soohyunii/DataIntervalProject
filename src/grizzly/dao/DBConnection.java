package grizzly.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class DBConnection {
	public static Connection dbConn;
	public static Connection getConnection()
	{
		Connection conn = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("연결할 DB정보를 입력해주세요.");
		System.out.print("접속이름(DB명) :  ");
		String userName = scan.nextLine(); 	/*접속이름(DB명)*/
		System.out.print("비밀번호 :  ");
		String password = scan.nextLine();	/*비밀번호*/
		System.out.print("호스트이름 :  ");
		String host = scan.nextLine();		/*호스트이름*/
		System.out.print("포트번호 :  ");
		String port = scan.nextLine();		/*포트번호*/
		System.out.print("SID :  ");
		String sid = scan.nextLine();		/*SID*/
		
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
