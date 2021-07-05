package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalDDB {

	 private Connection conn=null;
	 private Statement stmt=null;
	 private ResultSet rs = null;
	
	 private int rs1;
	 private PreparedStatement pstmt = null;
	 
	 private String RiderID;
		
	public RentalDDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
		    conn = DriverManager.getConnection(connectionUrl);
		    stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();		
		} 
	}
	
	
	
	public String getRiderID() {
		return RiderID;
	}
	public void setRiderID(String riderID) {
		RiderID = riderID;
	}
	
	
	
	public void closeDB()throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}
	
	private void getResultSet(String data)throws Exception{
		String sql;
		sql = "select 라이더ID,번호판 ,대여여부,대여일,대여신청 from 대여 where 라이더ID = '" + data +"' ";
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String num) throws Exception {
		getResultSet(num);
		return rs;
	}



	public void Delete(String riw ,String data, String rinumber, String riday) throws SQLException {
		String sql;
		sql="insert into 오토바이 values('"+rinumber+"', 'X', '"+riw+"','없음','" + data +"'); ";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		sql="update 대여   set 번호판='"+ rinumber +"' , 대여일='"+ riday +"', 대여신청 ='대여 중', 대여여부='대여완료' where 라이더ID = '"+  data +"';  ";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
		
		
		
		
	}
	

}