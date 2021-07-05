package Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RentalDServlet
 */
@WebServlet("/RentalDServlet")
public class RentalDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalDServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String	riw= request.getParameter("riw");
		String data = request.getParameter("data");
		String rinumber = request.getParameter("rinumber");
		String riday = request.getParameter("riday");
		
		
		try {
			RentalDDB obj = new RentalDDB();
			
			obj.Delete(riw,data,rinumber,riday);
			
			
			response.sendRedirect("admin/Rental.jsp");
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Noticelist.jsp");
		//	dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
