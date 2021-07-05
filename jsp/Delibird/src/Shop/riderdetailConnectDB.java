package Shop;
import java.sql.*;

public class riderdetailConnectDB {//DAO占쏙옙占쎌뿯 Data Access Object
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private  PreparedStatement ps;
	
	private String Username; //占쎌젎雅뚯눘�뵠�뵳占�
	private String Storeusername;//筌띲끉�삢占쎌뵠�뵳占�
	private String Type; //筌띲끉�삢 �넫�굝履� 占쎈립占쎈뻼,餓λ쵐�뻼 占쎌뵬占쎈뻼,占쎈쾻占쎈쾻 
	private String Address; //筌띲끉�삢雅뚯눘�꺖 
	private String Nun; //筌띲끉�삢占쎌읈占쎌넅甕곕뜇�깈
	
	ResultSet Search = null;

	public riderdetailConnectDB() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL 占쎄퐣甕곤옙
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
	
	public int delMemberlist(String id) throws Exception{
	    int result = 0;
	    
	
	    try{//占쎈뼄占쎈뻬
	    
	        ps = conn.prepareStatement("delete from 라이더호출 where 라이더ID = '" + id + "';");
	  
	        result = ps.executeUpdate();    
	           
	    }catch(Exception e){           
	        System.out.println(e+"=> delMemberlist fail");         
	    }finally{          
	    	closeDB();
	    }      
	   
	    return result;
	    }  
	

	
	
	private void getResultSet(String ID) throws Exception{
		String sql;
	
		
		sql = "select * from 라이더호출 where 매장번호 = (select 매장번호 from 점주 where ID = '"+ ID +"') ";
		

		rs = stmt.executeQuery(sql);
	}



	
	

	public ResultSet getRs(String ID) throws Exception{
		getResultSet(ID);
		return rs;
	}
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}
	
	
	
	public String getNun() {
		return Nun;
	}

	public void setNun(String nun) {
		this.Nun = nun;
	}
	
	
	
// 濡쒓렇�씤 留뚮뱾�뼱吏�硫� 荑쇰━臾� �닔�젙 
	public void getSearch(String id) {
		String sql;
		
		sql="select 호출번호,매장번호,라이더ID,호출날짜,호출시간 from 라이더호출 where 라이더ID like  '%" + id +"%'";
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
	
	
}
