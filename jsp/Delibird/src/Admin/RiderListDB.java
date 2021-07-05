package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RiderListDB {
	private Connection conn=null;
	 private Statement stmt=null;
	 private ResultSet rs = null;
	 
	public RiderListDB() {
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
	
	private void getResultSet(String name)throws Exception{
		String sql;
		if(name == "" || name == null)
			sql = "select * from 라이더";
		else
			sql = "select * from 라이더 where ID = '" + name + "'";
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String name) throws Exception {
		getResultSet(name);
		return rs;
	}

}
