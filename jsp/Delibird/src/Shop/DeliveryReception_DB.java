package Shop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DeliveryReception_DB {
	
	 Connection conn=null;
	  Statement stmt=null;
	 ResultSet rs = null;
	 int rs1;
 PreparedStatement pstmt = null;
 

	
 long time = System.currentTimeMillis(); 

	SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	String str = dayTime.format(new Date(time));



	




	public DeliveryReception_DB() {
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
		sql = "select * from 주문 where 주문번호 = " + num;
		rs = stmt.executeQuery(sql); 
	}

	public ResultSet getRs(String num) throws Exception {
		getResultSet(num);
		return rs;
	}
	
	public void Delete(String nu , String data , String guide ) throws Exception{
		
	  String inDate   = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
	  String inTime   = new java.text.SimpleDateFormat("HH시mm분ss초").format(new java.util.Date());


		String sql;
		sql = "update 주문 set 현재상황 ='조리중', 배달안내 = '"+ guide +"' where 주문번호 = " + data;
		rs1 = stmt.executeUpdate(sql);
		
		

		sql = "insert into 라이더호출(호출번호,매장번호,호출날짜,호출시간) values (" + data + ",'" + nu + "','"+ inDate +"','"+ inTime+ "')";
		
		rs1 = stmt.executeUpdate(sql);
		
		
	
	}
	
	public void cancel(String data) throws Exception{
		String sql = "update 주문 set 현재상황 = '주문취소' where 주문번호 = " + data;
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
	
	
	
	
	

}
