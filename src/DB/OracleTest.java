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
		
		Connection conn=null; 	// DB가 연결된 상태(세션)를 담은 객체
		PreparedStatement pstm=null;	// SQL문을 나타내는 객체
		ResultSet rs=null;	// 쿼리문을 날린것에 대한 반환값을 담을 객체 
		
		try {
			String quary = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE \r\n" + 
					"FROM USER_TAB_COLUMNS\r\n" + 
					"WHERE TABLE_NAME = 'NHIS_ORI_UP'AND DATA_TYPE='NUMBER'";
			
			conn=DBConnection.getConnection();
			pstm=conn.prepareStatement(quary);
			rs=pstm.executeQuery();
			
			System.out.println("NHIS_ORI_UP의 속성들 타입");
			System.out.println("==================================");
			
			while(rs.next()) {
				String tableName=rs.getString(1);
				String columnName=rs.getString(2);
				String dataType=rs.getString(3);
				
				String result=tableName+" "+columnName+" "+dataType;
				System.out.println(result);
			}
			
		} catch (SQLException sqle) {
			System.out.println("SELECT 문에서 예외 발생");
			sqle.printStackTrace();
		} finally {
			// DB연결 종료
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
