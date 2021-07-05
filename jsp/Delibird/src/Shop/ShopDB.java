package Shop;

import java.sql.*;

public class ShopDB {


	
	 private Connection conn=null;
	 private Statement stmt=null;
	
	 ResultSet rs;
	 
	 int rs1;
	  PreparedStatement ps;
	  

	
	public ShopDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL 占쎈쐻占쎈윞占쎈쭓占쎈탶�ⓦ끉�굲
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	void getResultSet(String ID)throws SQLException{
		String sql;
		sql = "select * from 매장  where 매장번호 = (select 매장번호 from 점주 where ID = '"+ ID +"')  ";
		rs = stmt.executeQuery(sql);
		
		}
	
	
	
	
	
//	void getResultSet()throws SQLException{
//		String sql;
//		sql = "select 留ㅼ옣踰덊샇,留ㅼ옣�씠由�,留ㅼ옣二쇱냼,留ㅼ옣�쟾�솕踰덊샇 from 留ㅼ옣";
//		rs = stmt.executeQuery(sql); 
//	}
//	
	
	
	
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



	public void getAddition(String shopname , String shopapp , String shopnumber, String number) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql;
		
		sql="update 매장 set 매장이름 ='"+ shopname  +"' , 매장주소='"+ shopapp  +"'  , 매장전화번호 ='"+ shopnumber  +"' where 매장번호 = " + number + "";
		rs1 = stmt.executeUpdate(sql);
		
	}
	
	

	
}
