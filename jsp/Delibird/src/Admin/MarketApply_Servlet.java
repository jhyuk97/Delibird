package Admin;

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
@WebServlet("/MarketApply_Servlet")
public class MarketApply_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketApply_Servlet() {
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
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/aaa/MarketApply.jsp");
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
		
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("jsonData");
		String id = data.substring(1, data.length()-1);
		try {
			MarketApplyConnectDB obj = new MarketApplyConnectDB();
			String email = obj.Email(id);
			SendEmail sd = new SendEmail("최재혁", email);
			sd.goEmail();
			obj.delMemberlist(id);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/aaa/MarketApply.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
