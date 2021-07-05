package Shop;

import java.sql.*;






public class AshopkeeperMemberForm {


	


	private Connection conn=null;
	 private Statement stmt=null;
	
	 ResultSet rs;
	 
	 int rs1;
	 int rs2;
	  PreparedStatement ps;
	private String sql;
	  

	private String sql2;

	
	
	

	
	public AshopkeeperMemberForm() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL 占쎈쐻占쎈윞占쎈쭓占쎈탶�ⓦ끉�굲
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void getResultSet()throws SQLException{
		
	
	}
	
	public ResultSet getRs() throws Exception{
		getResultSet();

		return rs;
	}




	
	
	
	public void closeDB() throws Exception{
		if(stmt != null)
			stmt.close();
		if(conn != null)
			conn.close();
	}



	





		public void getAddition(String id, String passwd, String tel01, String phone02, String mkl01, String mknum,
				String mkes, String sample4_roadAddress, String sample4_jibunAddress, String sample4_detailAddress,String email01) throws SQLException {
			// TODO Auto-generated method stub
			String fl = sample4_roadAddress + sample4_jibunAddress + sample4_detailAddress;
		
		// 	sql="insert into 오토바이 values('"+rinumber+"', 'X', '"+riw+"','없음','" + data +"'); ";
		
		
		sql="insert  into 점주  values('"+id+"','"+passwd+"',(NEXT VALUE  FOR 매장번호_seq),'"+tel01+"',"+phone02+" ,"+ mknum+")  ";
		rs1 = stmt.executeUpdate(sql);
		
		sql2="insert  into 매장신청  values('"+tel01+"','"+mkl01+"','"+fl+"','"+mkes+"',"+mknum+",(select 매장번호 from 점주 where ID ='"+id+"' ),'"+email01+"' )  ";
		rs2 = stmt.executeUpdate(sql2);
		
		
		
		
	}
	

	
}
