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
			
			// JDBC ����̹�(ojdbc6.jar)�� �ε��ϴ� �κ�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("Database�� ����Ǿ����ϴ�.\n");
			
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
