package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RealreportDetailConnectDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs;
	int rs1;



	String addition;



	
	public RealreportDetailConnectDB(){
		try {
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
	


	public ResultSet getRs(String data) throws Exception {
		getResultSet(data);
		return rs;
	}
	
	
	
//	
	
	private void getResultSet(String data) throws Exception{
		String sql;
		sql = "select 문의번호,작성자,분류,제목,내용,답변 from 문의 where 문의번호 =" + data;
		rs = stmt.executeQuery(sql);
	}	
	
	
	
	
	
	



	
	public void getAddition(String num1 ,String answer) throws SQLException {
		String sql;
		
		sql="update 문의 set 답변 ='" + answer + "' where 문의번호 = " + num1;
		rs1 = stmt.executeUpdate(sql);

	}
	
	
	public void setAddition(String addition) {
		this.addition = addition;
	}

	
		
	public void addition(String num1 ,String answer) throws SQLException{
		getAddition(num1,answer);
	return;
	
	
}



	
	
	
	

	
	
	
}
