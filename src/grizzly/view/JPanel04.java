package grizzly.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grizzly.dao.AfterDBConnection;
import grizzly.dao.InputColumn;
import grizzly.original.program.DBConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class JPanel04 extends JPanel{
	private JPanelNumber win;
	/*Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;*/
	
	public JPanel04(JPanelNumber win) {
		this.win=win;
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			JPanel01 jpanel01 = new JPanel01(win);
			String user=jpanel01.userName;
			String pw=jpanel01.password;
			String host=jpanel01.host;
			String port=jpanel01.port;
			String sid=jpanel01.sid;
			String url="jdbc:oracle:thin:@"+host+":"+port+":"+sid;
			
			conn=DriverManager.getConnection(url,user,pw);
			System.out.println("Database에 연결 확인!");
		} catch(SQLException sqle) {
			System.out.println("SQLException!!!");
		} catch(Exception e) {
			System.out.println("Unknown error!!!");
			e.printStackTrace();
		}
		
		JPanel03 jpanel03 = new JPanel03(win);
		JLabel labelTitle = new JLabel("유격조사 할 수치형 컬럼 한 가지 선택");
		
		String table = jpanel03.tableName;
		
		add(labelTitle);
		
		try {
			String numberColumn = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_NAME='"+table+"' AND DATA_TYPE='NUMBER'";
			/*String numberColumn = "SELECT * FROM "+table;*/
			System.out.println("numberColumn=="+numberColumn);
			pstm=conn.prepareStatement(numberColumn);
			System.out.println("pstm=="+pstm);
			rs=pstm.executeQuery();
			System.out.println("rs== "+rs);
		} catch (Exception sqle){
			System.out.println("SELECT 문에서 예외 발생");
			sqle.printStackTrace();	
		}
	}
	}
	
