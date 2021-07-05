package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RentalConnect {

	 private Connection conn=null;
	 private Statement stmt=null;
	 private String sql = null;
	 private ResultSet rs = null;
	 
	 private String RiderID;
	 private String NumberPlate;
	 private String Rent;
	 private String RentDate;
	
	 
	 public RentalConnect() {
		 
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
			sql = "select 라이더ID,번호판,대여여부,대여일,라이더.이름 ,대여신청 from 대여,라이더 where 대여.라이더ID = 라이더.ID and 대여여부 ='대여완료'"; 
			rs = stmt.executeQuery(sql); 
		}
			
		public String getRiderID() {
			return RiderID;
		}
		public void setRiderID(String riderID) {
			RiderID = riderID;
		}
		public String getNumberPlate() {
			return NumberPlate;
		}
		public void setNumberPlate(String numberPlate) {
			NumberPlate = numberPlate;
		}
		public String getRent() {
			return Rent;
		}
		public void setRent(String rent) {
			Rent = rent;
		}
		public String getRentDate() {
			return RentDate;
		}
		public void setRentDate(String rentDate) {
			RentDate = rentDate;
		}
		
		public ResultSet getRs() throws Exception{
			getResultSet();
			return rs;
		}
		
		public ResultSet getConnect(String searchName) throws Exception{
			RentalSearch(searchName);
			return rs;
		}
		
		void RentalSearch(String searchName) throws Exception{
			String sql;
			sql = "select * from 대여,라이더 where 대여.라이더ID = 라이더.ID and 대여여부 ='대여완료' and 라이더ID like '%" + searchName +"%'"; 
			rs = stmt.executeQuery(sql);
		}
		
		public void RentalConnect(String searchName) throws Exception {
			String sql;
			sql="select 라이더ID,번호판,대여여부,대여일 from 대여 where 라이더ID like  '%" + searchName +"%'";
			rs = stmt.executeQuery(sql);
			
		}
	
		
		
		
		
	
}
