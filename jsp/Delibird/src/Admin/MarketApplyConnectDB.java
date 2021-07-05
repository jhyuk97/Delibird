package Admin;
import java.sql.*;

public class MarketApplyConnectDB {//DAO���엯 Data Access Object
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	private  PreparedStatement ps;
	
	private String Username; //�젏二쇱씠由�
	private String Storeusername;//留ㅼ옣�씠由�
	private String Type; //留ㅼ옣 醫낅쪟 �븳�떇,以묒떇 �씪�떇,�벑�벑 
	private String Address; //留ㅼ옣二쇱냼 
	private String Nun; //留ㅼ옣�쟾�솕踰덊샇
	
	
	public MarketApplyConnectDB() {
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
	
	public int delMemberlist(String id) throws Exception{
	    int result = 0;
	    
	
	    try{//�떎�뻾
	    	String sql = "delete 매장신청 where 점주이름 = '" + id + "'";
	        ps = conn.prepareStatement(sql);
	        //?媛쒖닔留뚰겮 媛� 吏��젙
	        ps.executeUpdate();//荑쇰━�떎�뻾�쑝濡� �궘�젣�맂 �젅肄붾뱶 �닔 諛섑솚       
	           
	    }catch(Exception e){           
	        System.out.println(e+"=> delMemberlist fail");         
	    }finally{          
	    	closeDB();
	    }      
	   
	    return result;
	    }  
	
	public String Email(String id) throws Exception{
		String Email = "";
		try {
			String sql = "select * from 매장신청 where 점주이름 = '" + id + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Email = rs.getString("email");
			}
			
		}catch(Exception e) {
			e.getMessage();
		}
		
		return Email;
	}
	
	private void getApply(String data) throws Exception{
		String sql;
		sql = "select * from 매장신청 where 점주이름 = '" + data + "'";
		rs = stmt.executeQuery(sql);
	}
	
	public ResultSet Apply(String data) throws Exception{
		getApply(data);
		return rs;
	}
	
	private void getResultSet() throws Exception{
		String sql;
		sql = "select * from 매장신청";  //�엫�떆�뀒�씠釉� 留뚮뱾�뼱�꽌 �솗�씤�빐蹂닿린  
		//留ㅼ옣 �벑濡� �떊泥� �젏二� �씠由� / 留ㅼ옣�씠由� / 二쇱냼  / 留ㅼ옣醫낅쪟 
		rs = stmt.executeQuery(sql);
	}
	
	public ResultSet getdelRs(String id) throws Exception{
		delMemberlist(id);
		return rs;
	}

	public ResultSet getRs() throws Exception{
		getResultSet();
		return rs;
	}
	
}
