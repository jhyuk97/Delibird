package Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.MarketListConnect;

/**
 * Servlet implementation class Rental_Servlet
 */
@WebServlet("/Rental_Servlet")
public class Rental_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rental_Servlet() {
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
		request.setCharacterEncoding("UTF-8");
		try {
			MarketListConnect obj = new MarketListConnect();
			obj.setMarketNumber(request.getParameter("라이더ID"));
			obj.setMarketName(request.getParameter("번호판"));
			obj.setArea(request.getParameter("대여신청"));
			obj.setMarketPhone(request.getParameter("대여일"));
			request.setAttribute("conDB", obj);
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/Rental.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
	
		}
	}

}
