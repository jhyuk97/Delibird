package Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KoreanFoodUp
 */
@WebServlet("/KoreanFoodUp")
public class KoreanFoodUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KoreanFoodUp() {
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
		
		String date = request.getParameter("FoodName");
		String title = request.getParameter("FoodPhoto");
		String body = request.getParameter("FoodName");
		
		try {
			koreanFoodListDB DB = new koreanFoodListDB();
			DB.Write(title, body, date);
			response.sendRedirect("admin/koreanFoodList.jsp");
			
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Noticelist.jsp");
		//	dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
