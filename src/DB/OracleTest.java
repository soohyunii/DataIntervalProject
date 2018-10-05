package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.ResultSet;


public class OracleTest {
	
	public static void main(String[] args) {
		/*Connection dbConn;*/
		
		Connection conn=null; 	// DB�� ����� ����(����)�� ���� ��ü
		PreparedStatement pstm=null;	// SQL���� ��Ÿ���� ��ü
		ResultSet rs=null;	// �������� �����Ϳ� ���� ��ȯ���� ���� ��ü 
		
		try {
			String quary = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE \r\n" + 
					"FROM USER_TAB_COLUMNS\r\n" + 
					"WHERE TABLE_NAME = 'NHIS_ORI_UP'AND DATA_TYPE='NUMBER'";
			
			conn=DBConnection.getConnection();
			pstm=conn.prepareStatement(quary);
			rs=pstm.executeQuery();
			
			System.out.println("NHIS_ORI_UP�� �Ӽ��� Ÿ��");
			System.out.println("==================================");
			
			while(rs.next()) {
				String tableName=rs.getString(1);
				String columnName=rs.getString(2);
				String dataType=rs.getString(3);
				
				String result=tableName+" "+columnName+" "+dataType;
				System.out.println(result);
			}
			
		} catch (SQLException sqle) {
			System.out.println("SELECT ������ ���� �߻�");
			sqle.printStackTrace();
		} finally {
			// DB���� ����
			try {
				if(rs!=null) {rs.close();}
				if(pstm!=null) {pstm.close();}
				if(conn!=null) {conn.close();}
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
