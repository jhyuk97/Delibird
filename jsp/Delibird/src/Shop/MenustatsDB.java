package Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MenustatsDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public MenustatsDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL �꽌踰�
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

	public ResultSet getRs(String StartDay, String EndDay, String MarketNum) throws Exception{
		getResultSet(StartDay, EndDay, MarketNum);
		return rs;
	}

	private void getResultSet(String StartDay, String EndDay, String MarketNum) throws Exception{
		String sql;
		sql = "select distinct a.메뉴번호, a.메뉴명, (select count(*) from 주문,주문한음식,메뉴 d where 주문.주문번호 = 주문한음식.주문번호 and d.메뉴번호 = 주문한음식.메뉴번호 and d.메뉴명 = a.메뉴명 and 주문일 > '"+StartDay+"' and 주문일 < '"+EndDay+"' group by 메뉴명) as 횟수 from 메뉴 a, 주문 b where a.매장번호 = b.매장번호 and a.매장번호 = (select 매장번호 from 점주 where ID = '"+MarketNum+"')";
		rs = stmt.executeQuery(sql);

	}

}
