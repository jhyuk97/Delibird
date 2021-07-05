package Admin;
import java.sql.*;

public class MarketListConnect {
	 private Connection conn=null;
	 private Statement stmt=null;
	 private String sql = null;
	 private ResultSet rs = null;
	private String MarketNumber;
	private String Area;
	private String MarketName;
	private String MarketPhone;



public MarketListConnect() {
	
	
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

private void getResultSet()throws Exception{
	String sql;
	//sql = "insert into ����(�����ȣ,�����̸�,�����ּ�,������ȭ��ȣ) values('" + MarketNumber + "', '" + MarketName + "', '" + Area + "','" + MarketPhone + "')";
	//stmt.executeUpdate(sql);
	sql = "select 매장번호,매장이름,매장주소,매장전화번호 from 매장";
	rs = stmt.executeQuery(sql); 
}

private void getSearchSet(String id) throws Exception{
	String sql = "select 매장번호,매장이름,매장주소,매장전화번호 from 매장 where 매장이름 = '" + id + "'";
	rs = stmt.executeQuery(sql);
}

public ResultSet getSearch(String id) throws Exception{
	getSearchSet(id);
	return rs;
}

public String getMarketNumber() {
	return MarketNumber;
}
public void setMarketNumber(String marketNumber) {
	MarketNumber = marketNumber;
}
public String getMarketPhone() {
	return MarketPhone;
}
public void setMarketPhone(String marketPhone) {
	MarketPhone = marketPhone;
}
public String getArea() {
	return Area;
}
public void setArea(String area) {
	Area = area;
}
public String getMarketName() {
	return MarketName;
}
public void setMarketName(String marketName) {
	MarketName = marketName;
}

public ResultSet getRs() throws Exception{
	getResultSet();

	return rs;
}



	
	
	
}
