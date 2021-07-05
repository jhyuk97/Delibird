package Shop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class MenumanagementListDB {


		 private Connection conn=null;
		 private Statement stmt=null;
		 private ResultSet rs = null;
		 private PreparedStatement pstmt = null;
		 private ResultSet rs2 = null;

	public MenumanagementListDB() {
		
		
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

	private void getResultSet(String ID)throws Exception{
		String sql;
		sql = "select 메뉴번호,매장번호 ,메뉴명 from 메뉴 where 매장번호 = (select 매장번호 from 점주 where ID = '"+ ID +"') ";
		rs = stmt.executeQuery(sql); 
	}

	
	
	public ResultSet getRs(String ID) throws Exception {
		getResultSet(ID);
		return rs;
	}


	
	
	
	
	private void getResultSet2(String ID)throws Exception{
		String sql;
		sql = "select 매장번호 from 점주 where ID = '"+ ID +"' ";
		rs2 = stmt.executeQuery(sql); 
	}
	
	public ResultSet getRs2(String ID) throws Exception {
		getResultSet2(ID);
		return rs2;
	}

	public String ID ; //���� �� 

	public void MenuUpdata(String body, String mkNum ,String date, String mo, String photo) throws Exception{
		String sql;
		
		sql = "insert into 메뉴 values( "+ body +" , " + mkNum + ", '" + date + "', " + mo + ", '" + photo + "')";
		pstmt = conn.prepareStatement(sql);//메번, 매장번,메뉴명,금액
		pstmt.executeUpdate();
	}

	
	
	
	}
