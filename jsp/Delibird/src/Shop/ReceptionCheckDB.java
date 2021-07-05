package Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReceptionCheckDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public ReceptionCheckDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL �꽌踰�
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=0000;";
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
	
	public ResultSet getRs(String ID) throws Exception{
		ResultSet(ID);
		return rs;
	}
	
	void ResultSet(String ID) throws Exception{
		String sql;
		sql = "SELECT DISTINCT a.주문번호, 배송지, STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 ";
		sql = sql + "FOR XML PATH('') ),1,1,'') AS 메뉴 FROM 주문 a, 메뉴 b, 주문한음식 c ";
		sql = sql + "where a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and a.현재상황 = '주문옴' and a.매장번호 = (select 매장번호 from 점주 where ID = '" + ID +"')";
		rs = stmt.executeQuery(sql);
	}
}
