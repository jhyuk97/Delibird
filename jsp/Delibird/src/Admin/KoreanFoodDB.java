package Admin;

import java.sql.*;

public class KoreanFoodDB {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;

	
	public KoreanFoodDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL �꽌踰�
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void CloseDB() throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}
	
	public	 void InsertData(String fileName, String title, String detail, String foodname, String andImg	) throws Exception {
		String sql = "insert into 한식홍보 values(NEXT VALUE  FOR 한식홍보_seq, '" + fileName + "', '" + foodname + "', '" + detail + "', '" + title + "', '" + andImg + "')";
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}
	
}
