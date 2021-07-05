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
 * Servlet implementation class MarketDetailServlet
 */
@WebServlet("/RealreporttDetailServlet")
public class RealreporttDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealreporttDetailServlet() {
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
		String num = request.getParameter("num");
		String answer = request.getParameter("answer");
	
		try {
			RealreportDetailConnectDB obj = new RealreportDetailConnectDB();
			obj.getAddition(num, answer);
	
				
				response.setContentType("text/html; charset=euc-kr"); //한글이 인코딩
				PrintWriter out = response.getWriter(); //선언
				   
				String str="";
				str = "<script language='javascript'>"; //오프너 새로고침
				 str += "opener.location.reload();";
				str += "self.close();";   // 창닫기
				str += "</script>";
				out.print(str);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			
			}
	
	}
}
