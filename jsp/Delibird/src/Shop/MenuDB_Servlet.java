package Shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.NoticeDB;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/MenuDB_Servlet")
public class MenuDB_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuDB_Servlet() {
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
		//���� �ϴ°� 
		String data = request.getParameter("num");		
			MenuDB DB = new MenuDB();
			try {
				DB.Delete(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html; charset=euc-kr"); //한글이 인코딩
			   PrintWriter out = response.getWriter(); //선언
			   
			   String str="";
			   str = "<script language='javascript'>";
			   str += "opener.location.reload();";  //오프너 새로고침
			   str += "self.close();";   // 창닫기
			    str += "</script>";
			   out.print(str);
	

	
	}
	
}

