package Shop;

import java.sql.*;






public class StoreeditDB {


	


	private Connection conn=null;
	 private Statement stmt=null;
	
	 ResultSet rs;
	 
	 int rs1;
	  PreparedStatement ps;
	  

	

	
	
	

	
	public StoreeditDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL 占쎈쐻占쎈윞占쎈쭓占쎈탶�ⓦ끉�굲
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void getResultSet(String ID)throws SQLException{
		
	


		String sql;
		sql = "select * from 점주  where ID = '"+ ID + "' ";
		
		rs = stmt.executeQuery(sql); 
	}
	
	public ResultSet getRs(String ID) throws Exception{
		getResultSet(ID);

		return rs;
	}




	
	
	
	public void closeDB() throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}


//
//	public void getAddition(String shoppw, String number, String Myname, String Callnumber) throws SQLException {
//		// TODO Auto-generated method stub
//		
//		String sql;
//		
//		sql="update �젏二� set PW='"+ shoppw +"' , �씠由�='"+ Myname +"'  , �쟾�솕踰덊샇='"+ Callnumber +"'  , �궗�뾽�옄踰덊샇='"+ number +"'";
//		rs1 = stmt.executeUpdate(sql);
//		
//	}
	
	
public void getAddition(String shoppw, String number, String Myname, String Callnumber, String ID) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql;
		
		sql="update 점주  set PW='"+ shoppw +"' , 이름='"+ Myname +"'  , 전화번호='"+ Callnumber +"'  , 사업자번호='"+ number +"' where ID = '" + ID + "'";
		rs1 = stmt.executeUpdate(sql);
		
	}
	
	


	
}
