package Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarketList_Servlet
 */
@WebServlet("/MarketList_Servlet")
public class MarketList_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketList_Servlet() {
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
		try {
			MarketListConnect obj = new MarketListConnect();
			obj.setMarketNumber(request.getParameter("매장번호"));
			obj.setMarketName(request.getParameter("매장이름"));
			obj.setArea(request.getParameter("매장주소"));
			obj.setMarketPhone(request.getParameter("매장전화번호"));
			request.setAttribute("conDB", obj);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin/market.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
	
		}

	}

}
