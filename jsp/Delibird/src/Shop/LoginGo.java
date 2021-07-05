package Shop;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginGo
 */
@WebServlet("/LoginGo")
public class LoginGo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet Rs;
    
	private String ID,PW;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginGo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		ID = request.getParameter("ID");
		PW = request.getParameter("PW");
		
		String query = "select * from 점주 where id = '" + ID + "' and pw = '"+ PW + "'";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		    conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;");
		    stmt = conn.createStatement();
		    Rs = stmt.executeQuery(query);
		    if(Rs.next()) {
		    	HttpSession session = request.getSession();
		    	session.setAttribute("ID", ID);
		    	response.sendRedirect("shop/AchopkeeperMain.jsp");
		    }
		    else {
		    	response.sendRedirect("shop/AshopkeeperLoginForm.jsp");
		    }
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(Rs != null) Rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
