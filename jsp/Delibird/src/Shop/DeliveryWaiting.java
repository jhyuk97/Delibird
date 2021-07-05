package Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeliveryWaiting {
	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	
	public DeliveryWaiting() {
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
	
	void Resultset(String id) throws Exception {
		String sql;
		sql = "SELECT DISTINCT a.주문번호, 배송지, STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 ";
		sql = sql + "FOR XML PATH('') ),1,1,'') AS 메뉴, a.금액, d.호출시간, a.현재상황 FROM 주문 a, 메뉴 b, 주문한음식 c, 라이더호출 d ";
		sql = sql + "where a.주문번호 = d.호출번호 and a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and a.매장번호 = (select 매장번호 from 점주 where ID = '" + id +"') order by 호출시간 desc";
		rs = stmt.executeQuery(sql);
	}
	
	public ResultSet getRs(String id) throws Exception {
		Resultset(id);
		return rs;
	}
}
