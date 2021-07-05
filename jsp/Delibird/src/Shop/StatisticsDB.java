package Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticsDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
public StatisticsDB() {
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

public ResultSet getRs(String name, String StartDay, String EndDay) throws Exception{
	getResultSet(name, StartDay, EndDay);
	return rs;
}

private void getResultSet(String name, String StartDay, String EndDay) throws Exception{
	String sql;
	sql = "SELECT DISTINCT a.주문번호, 주문일, STUFF(( SELECT ',' + d.메뉴명 FROM 메뉴 d, 주문한음식 e where d.메뉴번호 = e.메뉴번호 and e.주문번호 = a.주문번호 "; 
	sql = sql + "FOR XML PATH('') ),1,1,'') AS 메뉴, 배송지, a.금액 FROM 주문 a, 메뉴 b, 주문한음식 c ";
	sql = sql + "where a.주문번호 = c.주문번호 and c.메뉴번호 = b.메뉴번호 and (a.현재상황 = '배달완료' or a.현재상황 = '리뷰작성완료')  and a.매장번호 = (select 매장번호 from 점주 where ID = '"+ name+"') and 주문일 > '" + StartDay + "' and 주문일 < '" + EndDay + "'";
	rs = stmt.executeQuery(sql);
}

public ResultSet getRs2(String name, String StartDay, String EndDay) throws Exception{
	getResultSet2(name, StartDay, EndDay);
	return rs;
}

private void getResultSet2(String name, String StartDay, String EndDay) throws Exception{
	String sql;
	sql = "select count(*) as 판매수, sum(금액) as 총액 from 주문 where (현재상황 = '배달완료' or 현재상황 = '리뷰작성완료') and 매장번호 = (select 매장번호 from 점주 where ID = '"+name+"') and 주문일 > '"+ StartDay +"' and 주문일 < '" + EndDay + "'";
	rs = stmt.executeQuery(sql);
}

}
