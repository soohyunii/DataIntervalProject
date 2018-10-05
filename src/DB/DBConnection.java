package DB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	public static Connection dbConn;
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			String user="WIZARD";
			String pw="tiger";
			String url="jdbc:oracle:thin:@192.168.0.99:1521:orcl";
			
			// JDBC 드라이버(ojdbc6.jar)를 로딩하는 부분
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("Database에 연결되었습니다.\n");
			
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
