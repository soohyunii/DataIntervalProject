package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.ResultSet;

public class InputColumns {

	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstm=null;
		PreparedStatement pstm2=null;
		PreparedStatement pstm3=null;
		PreparedStatement pstm4=null;
		PreparedStatement pstm5=null;
		PreparedStatement pstm6=null;
		PreparedStatement pstm7=null;
		PreparedStatement pstm8=null;
		PreparedStatement pstm9=null;
		PreparedStatement pstm10=null;
		PreparedStatement pstm11=null;
		PreparedStatement pstm12=null;
		PreparedStatement pstm13=null;
		PreparedStatement pstm13_1=null;
		PreparedStatement pstm14=null;
		PreparedStatement pstm15=null;
		PreparedStatement pstm16=null;
		PreparedStatement pstm17=null;
		PreparedStatement pstm18=null;
		PreparedStatement pstm18_1=null;
		PreparedStatement pstm19=null;
		PreparedStatement pstm20=null;
		PreparedStatement pstm21=null;
		PreparedStatement pstm22=null;
		PreparedStatement pstm23=null;
		PreparedStatement pstm24=null;
		PreparedStatement pstm25=null;
		PreparedStatement pstm26=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		ResultSet rs9=null;
		ResultSet rs10=null;
		ResultSet rs11=null;
		ResultSet rs12=null;
		ResultSet rs13=null;
		ResultSet rs13_1=null;
		ResultSet rs14=null;
		ResultSet rs15=null;
		ResultSet rs16=null;
		ResultSet rs17=null;
		ResultSet rs18=null;
		ResultSet rs18_1=null;
		ResultSet rs19=null;
		ResultSet rs20=null;
		ResultSet rs21=null;
		ResultSet rs22=null;
		ResultSet rs23=null;
		ResultSet rs24=null;
		ResultSet rs25=null;
		ResultSet rs26=null;
		
		String colName;
		/*String result = null;*/
		
		Scanner scan = new Scanner(System.in);
		System.out.println("유격조사할 컬럼명을 입력하세요.");
		colName=scan.nextLine();
		
		
		
		try {
			String totalCount ="SELECT COUNT(*) FROM TEMP1_"+colName;
			String quary="CREATE TABLE TEMP1_"+colName+" AS (SELECT "+colName+", EMAIL AS ORI_PK FROM NHIS_ORI_UP_COPY WHERE "+colName+" IS NOT NULL) ORDER BY "+colName;
			String quary2="ALTER TABLE TEMP1_"+colName+" ADD (PK1 NUMBER)";
			String quary3="UPDATE TEMP1_"+colName+" SET PK1=ROWNUM";
			String quary4="CREATE TABLE TEMP2_"+colName+" AS (SELECT "+colName+", EMAIL AS ORI_PK FROM NHIS_ORI_UP_COPY WHERE "+colName+" IS NOT NULL) ORDER BY "+colName;;
			String quary5="ALTER TABLE TEMP2_"+colName+" ADD (PK2 NUMBER)";
			String quary6="UPDATE TEMP2_"+colName+" SET PK2=ROWNUM+1";
			String quary7="CREATE TABLE DIST_NEXT_"+colName+" AS (SELECT ROWNUM AS PK, A."+colName+" - B."+colName+
					" AS DIST_NEXT FROM TEMP1_"+colName+" A, TEMP2_"+colName+" B WHERE A.PK1=B.PK2)";
			
			String quary9="ALTER TABLE DIST_NEXT_"+colName+" ADD (PK0 NUMBER)";
			String quary10="UPDATE DIST_NEXT_"+colName+" SET PK0=ROWNUM-1";
			String quary11="CREATE TABLE DIST_PREV_"+colName+" AS SELECT PK, DIST_NEXT FROM DIST_NEXT_"+colName;
			String quary12="INSERT INTO DIST_PREV_"+colName+" (PK, DIST_NEXT) VALUES (0,0)";
			String quary14="ALTER TABLE DIST_PREV_"+colName+" RENAME COLUMN PK TO PK0";
			String quary15="ALTER TABLE DIST_PREV_"+colName+" RENAME COLUMN DIST_NEXT TO DIST_PREV";
			String quary16="CREATE TABLE DIST_BOTH_"+colName+" AS SELECT A.PK, A.DIST_NEXT, B.DIST_PREV FROM DIST_NEXT_"+colName+
					" A, DIST_PREV_"+colName+" B WHERE A.PK0=B.PK0 ORDER BY A.PK";
			String quary17="ALTER TABLE DIST_BOTH_"+colName+" ADD (FLAG NUMBER)";
			String quary20="UPDATE DIST_BOTH_"+colName+" SET FLAG=0 WHERE PK=1";
			String quary21="SELECT TRUNC(AVG(DIST_NEXT)+3*(STDDEV_POP(DIST_NEXT)),6) FROM DIST_BOTH_"+colName;
			String quary23="CREATE TABLE ONLY_OUT_"+colName+" AS SELECT * FROM DIST_INOUT_"+colName+" WHERE INOUT='OUT'";
			String quary24="SELECT COUNT(*) FROM ONLY_OUT_"+colName;
			String quary25="CREATE TABLE FINAL_"+colName+" AS SELECT A.ORI_PK, B.PK, B.DIST_NEXT, B.DIST_PREV, B.FLAG, B.INOUT"
					+ " FROM TEMP1_"+colName+" A, ONLY_OUT_"+colName+" B WHERE A.PK1=B.PK";
			
			
			System.out.println("==============================");		
			
			conn=DBConnection.getConnection();
			
			pstm=conn.prepareStatement(quary);
			rs=pstm.executeQuery();
			System.out.println("TEMP1 테이블 생성됨 ");
			
			pstm2=conn.prepareStatement(quary2);
			rs2=pstm2.executeQuery();
			System.out.println("TEMP1 테이블에 PK1 컬럼 생성");
			
			pstm3=conn.prepareStatement(quary3);
			rs3=pstm3.executeQuery();
			System.out.println("PK1 컬럼에 rownum 생성");
			
			pstm4=conn.prepareStatement(quary4);
			rs4=pstm4.executeQuery();
			System.out.println("TEMP2 테이블 생성됨");
			
			pstm5=conn.prepareStatement(quary5);
			rs5=pstm5.executeQuery();
			System.out.println("TEMP2 테이블에 PK2 컬럼 생성");
			
			pstm6=conn.prepareStatement(quary6);
			rs6=pstm6.executeQuery();
			System.out.println("PK2 컬럼에 rownum+1 생성");
			
			pstm7=conn.prepareStatement(quary7);
			rs7=pstm7.executeQuery();
			System.out.println("DIST_NEXT 테이블 생성");
			
			pstm8=conn.prepareStatement(totalCount);
			rs8=pstm8.executeQuery();
			
			while(rs8.next()) {
				String data=rs8.getString(1);
				String result=data;
				System.out.println(result);
				/*result값 = (insert 하려는) N값*/
				
				String quary8="INSERT INTO DIST_NEXT_"+colName+" VALUES ("+result+",0)";
				pstm9=conn.prepareStatement(quary8);
				rs9=pstm9.executeQuery();
				System.out.println("DIST_NEXT 테이블에 INSERT VALUES(N,0)");			
			}
			
			pstm10=conn.prepareStatement(quary9);
			rs10=pstm10.executeQuery();
			System.out.println("DIST_NEXT 테이블에 PK0컬럼 생성");
			
			pstm11=conn.prepareStatement(quary10);
			rs11=pstm11.executeQuery();
			System.out.println("PK0컬럼에 ROWNUM-1 생성");
			
			pstm12=conn.prepareStatement(quary11);
			rs12=pstm12.executeQuery();
			System.out.println("DIST_PREV 테이블 생성");
			
			pstm13=conn.prepareStatement(quary12);
			rs13=pstm13.executeQuery();
			System.out.println("DIST_PREV 테이블에 (0,0)값 삽입");
			
			pstm13_1=conn.prepareStatement(totalCount);
			rs13_1=pstm13_1.executeQuery();
			
			
			while(rs13_1.next()) {
				String data=rs13_1.getString(1);
				String result=data;
				System.out.println(result);
				/*result값 = (insert 하려는) N값*/
				
				String quary13="DELETE FROM DIST_PREV_"+colName+" WHERE PK="+result;
				pstm14=conn.prepareStatement(quary13);
				rs14=pstm14.executeQuery();
				System.out.println("DIST_PREV 테이블에 N값 삭제");
			}
			
			pstm15=conn.prepareStatement(quary14);
			rs15=pstm15.executeQuery();
			System.out.println("DIST_PREV 테이블의 PK컬럼명 PK0으로 변경");
			
			pstm16=conn.prepareStatement(quary15);
			rs16=pstm16.executeQuery();
			System.out.println("DIST_PREV 테이블의 DIST_NEXT컬럼명 DIST_PREV로 변경");

			pstm17=conn.prepareStatement(quary16);
			rs17=pstm17.executeQuery();
			System.out.println("DIST_BOTH 테이블 생성");
			
			pstm18=conn.prepareStatement(quary17);
			rs18=pstm18.executeQuery();
			System.out.println("DIST_BOTH 테이블에 FLAG 컬럼 생성");

			pstm18_1=conn.prepareStatement(totalCount);
			rs18_1=pstm18_1.executeQuery();
			
			while(rs18_1.next()) {
				String data=rs18_1.getString(1);
				String result=data;
				System.out.println(result);
				/*result값 = (insert 하려는) N값*/
				
				String quary18="UPDATE DIST_BOTH_"+colName+" SET FLAG=1 WHERE PK>1 AND PK<"+result;
				String quary19="UPDATE DIST_BOTH_"+colName+" SET FLAG=0 WHERE PK="+result;
				
				pstm19=conn.prepareStatement(quary18);
				rs19=pstm19.executeQuery();
				System.out.println("DIST_BOTH 테이블에 FLAG=1값 부여");
				
				pstm20=conn.prepareStatement(quary19);
				rs20=pstm20.executeQuery();
				System.out.println("DIST_BOTH 테이블에 FLAG=0값 부여 (PK=N값)");
			}
			
			pstm21=conn.prepareStatement(quary20);
			rs21=pstm21.executeQuery();
			System.out.println("DIST_BOTH 테이블에 FLAG=0값 부여 (PK=1값)");
			
			pstm22=conn.prepareStatement(quary21);
			rs22=pstm22.executeQuery();
			System.out.println("평균, 표준편차 값 구하기");
			
			while(rs22.next()) {
				String data=rs22.getString(1);
				String result=data;
				System.out.println(result);
				
				String quary22="CREATE TABLE DIST_INOUT_"+colName+" AS SELECT PK, DIST_NEXT, DIST_PREV, FLAG, "
						+ "CASE WHEN FLAG=1 THEN CASE WHEN DIST_NEXT >= '"+result+"' AND DIST_PREV >= '"+result+"' THEN 'OUT'"
								+ " ELSE 'IN' END "
								+ "WHEN FLAG=0 THEN CASE WHEN DIST_NEXT >= '"+result+"' OR DIST_PREV >= '"+result+"' THEN 'OUT'"
										+ " ELSE 'IN' END"
										+ " END AS INOUT FROM DIST_BOTH_"+colName;
				
				pstm23=conn.prepareStatement(quary22);
				rs23=pstm23.executeQuery();
				System.out.println("INOUT 테이블 생성");	
			}
			
			pstm24=conn.prepareStatement(quary23);
			rs24=pstm24.executeQuery();
			System.out.println("ONLY_OUT 테이블이 생성되었습니다.");
			
			pstm25=conn.prepareStatement(quary24);
			rs25=pstm25.executeQuery();
			
			while(rs25.next()) {
				String data=rs25.getString(1);
				String result=data;
				
				System.out.println("제거해야 할 데이터는 총 "+result+"개 입니다.");
				
			}
			
			pstm26=conn.prepareStatement(quary25);
			rs26=pstm26.executeQuery();
			System.out.println("최종 테이블까지 전부 생성되었습니다. FINAL 테이블을 확인하고 ORI_PK키로 원본과 JOIN해 삭제하세요.");
			
			
		} catch (SQLException sqle) {
			System.out.println("SELECT 문에서 예외 발생");
			sqle.printStackTrace();
	} finally {
		try {
			if(rs!=null) {rs.close();}
			if(rs2!=null) {rs2.close();}
			if(rs3!=null) {rs3.close();}
			if(rs4!=null) {rs4.close();}
			if(rs5!=null) {rs5.close();}
			if(rs6!=null) {rs6.close();}
			if(rs7!=null) {rs7.close();}
			if(rs8!=null) {rs8.close();}
			if(rs9!=null) {rs9.close();}
			if(rs10!=null) {rs10.close();}
			if(rs11!=null) {rs11.close();}
			if(rs12!=null) {rs12.close();}
			if(rs13!=null) {rs13.close();}
			if(rs13_1!=null) {rs13_1.close();}
			if(rs14!=null) {rs14.close();}
			if(rs15!=null) {rs15.close();}
			if(rs16!=null) {rs16.close();}
			if(rs17!=null) {rs17.close();}
			if(rs18!=null) {rs18.close();}
			if(rs18_1!=null) {rs18_1.close();}
			if(rs19!=null) {rs19.close();}
			if(rs20!=null) {rs20.close();}
			if(rs21!=null) {rs21.close();}
			if(rs22!=null) {rs22.close();}
			if(rs23!=null) {rs23.close();}
			if(rs24!=null) {rs24.close();}
			if(rs25!=null) {rs25.close();}
			if(rs26!=null) {rs26.close();}
			if(pstm!=null) {pstm.close();}
			if(pstm2!=null) {pstm2.close();}
			if(pstm3!=null) {pstm3.close();}
			if(pstm4!=null) {pstm4.close();}
			if(pstm5!=null) {pstm5.close();}
			if(pstm6!=null) {pstm6.close();}
			if(pstm7!=null) {pstm7.close();}
			if(pstm8!=null) {pstm8.close();}
			if(pstm9!=null) {pstm9.close();}
			if(pstm10!=null) {pstm10.close();}
			if(pstm11!=null) {pstm11.close();}
			if(pstm12!=null) {pstm12.close();}
			if(pstm13!=null) {pstm13.close();}
			if(pstm13_1!=null) {pstm13_1.close();}
			if(pstm14!=null) {pstm14.close();}
			if(pstm15!=null) {pstm15.close();}
			if(pstm16!=null) {pstm16.close();}
			if(pstm17!=null) {pstm17.close();}
			if(pstm18!=null) {pstm18.close();}
			if(pstm18_1!=null) {pstm18_1.close();}
			if(pstm19!=null) {pstm19.close();}
			if(pstm20!=null) {pstm20.close();}
			if(pstm21!=null) {pstm21.close();}
			if(pstm22!=null) {pstm22.close();}
			if(pstm23!=null) {pstm23.close();}
			if(pstm24!=null) {pstm24.close();}
			if(pstm25!=null) {pstm25.close();}
			if(pstm26!=null) {pstm26.close();}
			if(conn!=null) {conn.close();}
			
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage());
			}
	 	}
	}
}

