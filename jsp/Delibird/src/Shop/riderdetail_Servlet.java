package Shop;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.json.simple.JSONValue;

import Admin.MarketApplyConnectDB;

/**
 * Servlet implementation class MarketApply_Servlet
 */
@WebServlet("/riderdetail_Servlet")
public class riderdetail_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public riderdetail_Servlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("id");
		
		try {
			MarketApplyConnectDB obj = new MarketApplyConnectDB();
			obj.delMemberlist(data);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/MarketApply.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String data = request.getParameter("SearchName");
		request.setCharacterEncoding("UTF-8");
		try {
				
			request.setAttribute("SearchName", data);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/shop/riderdetail.jsp");
				dispatcher.forward(request, response);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			
			}
	
	}

}
