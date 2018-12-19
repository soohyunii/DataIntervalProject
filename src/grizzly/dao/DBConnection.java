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
		System.out.println("������ DB������ �Է����ּ���.");
		System.out.print("�����̸�(DB��) :  ");
		String userName = scan.nextLine(); 	/*�����̸�(DB��)*/
		System.out.print("��й�ȣ :  ");
		String password = scan.nextLine();	/*��й�ȣ*/
		System.out.print("ȣ��Ʈ�̸� :  ");
		String host = scan.nextLine();		/*ȣ��Ʈ�̸�*/
		System.out.print("��Ʈ��ȣ :  ");
		String port = scan.nextLine();		/*��Ʈ��ȣ*/
		System.out.print("SID :  ");
		String sid = scan.nextLine();		/*SID*/
		
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
