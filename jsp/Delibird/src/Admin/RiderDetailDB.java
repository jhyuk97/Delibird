package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RiderDetailDB {
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs = null;
	
	public RiderDetailDB() {
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
	
	private void getResultSet(String ID,String StartDate, String EndDate)throws Exception{
		String sql;
		if(StartDate == null || StartDate == "")
			sql = "select * from 라이더호출 where 라이더ID = '" + ID + "'";
		else
			sql = "select * from 라이더호출 where 라이더ID = '" + ID + "' and 호출날짜 >= '" + StartDate +"' and 호출날짜 <= '" + EndDate+ "'";
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String ID,String StartDate, String EndDate) throws Exception {
		getResultSet(ID, StartDate, EndDate);
		return rs;
	}
}
