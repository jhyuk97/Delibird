package Admin;
import java.io.Serializable;
import java.sql.*;

public class RealreportConnect {


	
	 private Connection conn=null;
	 private Statement stmt=null;
	
	 private ResultSet rs = null;

	 public int num1; // 臾몄쓽 踰덊샇
	 
		public	String name; //�옉�꽦�옄
		 public	String title;//�젣紐�
		 public String content;//�궡�슜
		 public String yesno; //�떟蹂�
		 public String result; //寃곌낵 

		 public	String vw; //遺꾨쪟
		 
	  PreparedStatement ps;
		

	public int getNum1() {
		return (int) num1;
	}

	public void setNum1(String string, int num1) {
		this.num1= num1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getYesno() {
		return yesno;
	}

	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	 public String getVw() {
			return vw;
		}

		public void setVw(String vw) {
			this.vw = vw;
		}


	
	
	
	public RealreportConnect() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL �뜝�럡�맋�뵓怨ㅼ삕
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void getResultSet()throws Exception{
		String sql;
		sql = "select 문의번호,작성자,분류,제목,내용,답변 from 문의";
		rs = stmt.executeQuery(sql); 
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
	

	
	

	

	
	
}
