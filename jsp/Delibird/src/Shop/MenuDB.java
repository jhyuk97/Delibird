package Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuDB {
	 private Connection conn=null;
	 private Statement stmt=null;
 ResultSet rs = null;
 int rs1;
	 private PreparedStatement pstmt = null;
	 
	public MenuDB() {
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

	
	
	
	private void getResultSet(String num ,String ID)throws Exception{
	
		String sql;
		sql = "select * from 메뉴 where 메뉴번호 ="+	num+" and 매장번호 =(select 매장번호 from 점주 where ID  ='"+ ID +"' ) ";
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String num,String ID) throws Exception {
		getResultSet(num,ID);
		return rs;
	}
	

	
	public void Delete(String data) throws Exception{
		String sql;
		sql = "delete from 메뉴 where 메뉴번호 = " + data;
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}


//
//	public void getdelRs(String Mnumber1, String name1) {
//		String sql;
//		sql="update �޴� set �޴���='"+ name + "' where �޴���ȣ ="+ Mnumber +" ";
//		

	public void getdelRs(String data) throws SQLException {
		String sql;
		sql="update 메뉴 set 메뉴명='"+ data + "' ";
		rs1 = stmt.executeUpdate(sql);
		
		

	
		
	}



	

	
}
