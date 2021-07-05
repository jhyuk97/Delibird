package Shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.NoticeDB;

/**
 * Servlet implementation class riderdetail_delete_Servlet
 */
@WebServlet("/riderdetail_delete_Servlet")
public class riderdetail_delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public riderdetail_delete_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String data = request.getParameter("num");
		try {
			riderdetail_delete_DB DB = new riderdetail_delete_DB();
			DB.Delete(data);
			
			response.sendRedirect("shop/riderdetail_delete.jsp");
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Noticelist.jsp");
		//	dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
