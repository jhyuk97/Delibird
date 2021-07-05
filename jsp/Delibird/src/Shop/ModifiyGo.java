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
 * Servlet implementation class ModifiyGo
 */
@WebServlet("/ModifiyGo")
public class ModifiyGo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement stmt;
    
	private String �̸�, ID, PW, ��ȭ��ȣ;
	
	HttpSession httpSession;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifiyGo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		httpSession = request.getSession();
		
		�̸� = request.getParameter("�̸�");
		ID = request.getParameter("ID");
		PW = request.getParameter("PW");
		
		��ȭ��ȣ = request.getParameter("��ȭ��ȣ");
		
		if(pwConfirm()) {
			System.out.println("OK");
			
			String query = "update ����� set �̸� = '" + �̸� +"', ��ȭ��ȣ = " + ��ȭ��ȣ + "";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			    connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;");
			    stmt = connection.createStatement();
			    int i = stmt.executeUpdate(query);
			    if(i ==1)
			    {
			    	System.out.println("update Success");
			    	httpSession.setAttribute("�̸�", �̸�);
			    	response.sendRedirect("http://localhost:8080/Delibird/shop/ModifiyResult.jsp");
			    	
			    }else
			    {
			    	System.out.println("update fail");
			    	response.sendRedirect("http://localhost:8080/Delibird/shop/modify.jsp");
			    }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(stmt != null) stmt.close();
					if(connection != null) connection.close();
				}catch (Exception e) {}
			}
		}else {
			System.out.println("NG");
		}
			
		
	}
	private boolean pwConfirm() {
		boolean rs = false;
		
		String sessionPw = (String)httpSession.getAttribute("PW");
		if(sessionPw.contentEquals(PW)) {
			rs = true;
			}else {
				rs = false;
			}
		return rs;
	}
}


