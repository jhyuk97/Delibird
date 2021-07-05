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

import Admin.RealreportConnect;

/**
 * Servlet implementation class MarketApply_Servlet
 */
@WebServlet("/Realreport_Servlet")
public class Realreport_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Realreport_Servlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("UTF-8");
		try {
			
			RealreportConnect obj = new RealreportConnect();
			obj.setVw(request.getParameter("문의번호"));
			obj.setName(request.getParameter("작성자"));
			obj.setVw(request.getParameter("분류"));
			
			obj.setContent(request.getParameter("내용"));
			obj.setYesno(request.getParameter("답변"));
			obj.setResult(request.getParameter("결과"));	
				
			request.setAttribute("conDB", obj);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Realreport.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
	
		}

	}

}