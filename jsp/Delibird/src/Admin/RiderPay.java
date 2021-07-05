package Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RiderPay
 */
@WebServlet("/RiderPay")
public class RiderPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderPay() {
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
		String date = request.getParameter("jsonData1");//""2020-1""
		String ID = request.getParameter("jsonData2");
		String money = request.getParameter("jsonData3");
		String data1 = date.substring(1, 7);
		String data2 = ID.substring(1,3);
		String data3 = money.substring(1, 6);
		try {
			request.setAttribute("test", "true");
			RiderPayDB obj = new RiderPayDB();
			obj.getPaySet(data2, Integer.parseInt(data3), data1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/aaa/MarketApply.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
