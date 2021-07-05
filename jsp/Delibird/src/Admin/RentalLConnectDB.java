package Admin;
import java.sql.*;

public class RentalLConnectDB {//DAO占쏙옙占쎌뿯 Data Access Object
	 Connection conn = null;
	 Statement stmt = null;
	ResultSet rs = null;
PreparedStatement ps;



ResultSet Search = null;







	private String RiderID; //占쎌뵬占쎌뵠占쎈쐭 占쎈툡占쎌뵠占쎈탵
	private int Number;    //甕곕뜇�깈占쎈솇
	private String YesNo;//占쏙옙占쎈연 占쎈연�겫占� 
	private String Name; // 占쎌뵬占쎌뵠占쎈쐭 占쎌뵠�뵳占� 
	private String day; //  占쏙옙占쎈연 占쎌뵬 
	
	public RentalLConnectDB(){
		
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL �뜝�럡�맋�뵓怨ㅼ삕
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
	
	
	
	
	

	private void getResultSet() {
		String sql;
	
		
		sql = "select 라이더ID,번호판,이름,대여일 from 대여,라이더 where 대여.라이더ID = 라이더.ID";   
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	public void getSearch(String id) {
		String sql;
		
		sql="select 라이더ID,번호판,대여여부,이름,대여일 from 대여,라이더 where 대여.라이더ID = 라이더.ID and 라이더ID like '%" + id +"%'";
		try {
			Search = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


	public ResultSet Search(String id){
		getSearch(id);
		return Search;
	}
	
	public ResultSet getdelRs(String id) throws Exception{
		getSearch(id);
		return rs;
	}
	

public void setSearch(ResultSet search) {
	
	
	Search = search;
}
	

//占쎈연疫꿸퀗�돱筌욑옙 


	
	
	public String getRiderID() {
		return RiderID;
	}
	public void setRiderID(String riderID) {
		RiderID = riderID;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public String getYesNo() {
		return YesNo;
	}
	public void setYesNo(String yesNo) {
		YesNo = yesNo;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

	
	
	public ResultSet getRs(){
		getResultSet();
		return rs;
	}


	
	

}

