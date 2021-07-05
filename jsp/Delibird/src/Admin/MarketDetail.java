package Admin;

import java.sql.*;
import java.util.Calendar;

public class MarketDetail {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs;
	Calendar cal = Calendar.getInstance();
	public int year = cal.get(Calendar.YEAR);
	public int month = cal.get(Calendar.MONTH) + 1;
	public MarketDetail(){
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
	
	private void getResultSet(String data) throws Exception{
		String sql;
		sql = "select 메뉴명, 금액, 주문일 from 메뉴 m,주문 o,주문한음식 f,매장 k where m.매장번호 = o.매장번호 and o.주문번호 = f.주문번호 and m.메뉴번호 = f.메뉴번호 and k.매장번호 = m.매장번호 and k.매장이름 = '" + data + "';";
		rs = stmt.executeQuery(sql);
	}

	public ResultSet getRs(String data) throws Exception {
		getResultSet(data);
		return rs;
	}
	
	public ResultSet getRs(String data, int year, int month) throws Exception{
		getResultSet(data, year, month);
		return rs;
	}
	
	private void getResultSet(String data, int year, int month) throws Exception{
		String sql;
		sql = "select 메뉴명, 금액, 주문일 from 메뉴 m,주문 o,주문한음식 f,매장 k where m.매장번호 = o.매장번호 and o.주문번호 = f.주문번호 and m.메뉴번호 = f.메뉴번호 and k.매장번호 = m.매장번호 and k.매장이름 = '"+ data +"' and 주문일 like '%"+ Integer.toString(year) + "-" + Integer.toString(month) + "%';";
		rs = stmt.executeQuery(sql);
	}	
	
}
