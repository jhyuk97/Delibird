package Admin;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class RiderPayDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Calendar cal = Calendar.getInstance();
	public int year = cal.get(Calendar.YEAR);
	public int month = cal.get(Calendar.MONTH) + 1;
	
	public RiderPayDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void closeDB() throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}
	
	private void getResultSet() {
		String sql;
	
		
		sql = "select distinct SUBSTRING(ȣ�⳯¥,0,7),���̴�ID, (select COUNT(*) from ���̴�ȣ�� where ���̴�ID = 'aa' and ȣ�⳯¥ like '%2020-2%'), (select COUNT(*)*2000 from ���̴�ȣ�� where ���̴�ID = 'aa' and ȣ�⳯¥ like '%2020-2%') from ���̴�ȣ��";  
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public ResultSet getRs(){
		getResultSet();
		return rs;
	}
	
	public ResultSet getRs(int year, int month){
		getResultSet(year, month);
		return rs;
	}
	
	private void getResultSet(int year, int month) {
		String sql;
		sql = "select distinct SUBSTRING(호출날짜,0,7),라이더ID, (select COUNT(*) from 라이더호출 where 라이더ID = 'aa' and 호출날짜 like '%"+ Integer.toString(year) + "-" + Integer.toString(month) + "%'), (select COUNT(*)*2000 from 라이더호출 where 라이더ID = 'aa' and 호출날짜 like '%"+ Integer.toString(year) + "-" + Integer.toString(month) + "%') from 라이더호출 where 호출날짜 like '%" + Integer.toString(year) + "-" + Integer.toString(month) + "%'";  
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getPaySet(String ID, int money, String date) {
		String sql;
		try {
			sql = "insert into 급여 values(NEXT VALUE FOR riderpay_seq, '" + ID + "', " + money + ", '" + date +"')";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
