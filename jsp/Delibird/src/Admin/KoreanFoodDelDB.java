package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class KoreanFoodDelDB {
	
	 private Connection conn=null;
	 private Statement stmt=null;
	 private ResultSet rs = null;
	 private PreparedStatement pstmt = null;
	 
	public KoreanFoodDelDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
		    conn = DriverManager.getConnection(connectionUrl);
		    stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();		
		} 
	}
	
	public void closeDB()throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}
	
	private void getResultSet(String num)throws Exception{
		String sql;
		sql = "select * from 한식홍보 where 홍보번호 = " + num;
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String num) throws Exception {
		getResultSet(num);
		return rs;
	}
	
	public void Delete(String data) throws Exception{
		String sql;
		sql = "delete from 한식홍보 where 홍보번호 = " + data;
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
}
