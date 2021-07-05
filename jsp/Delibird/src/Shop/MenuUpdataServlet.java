package Shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/MenuUpdataServlet")
public class MenuUpdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuUpdataServlet() {
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
		
		String photo = request.getParameter("photo");
		String fileName = "/storage/emulated/0/DCIM/Camera/" + photo.substring( photo.lastIndexOf("\\")+1, photo.length() );

		String mkNum = request.getParameter("mkNum");
		
		String title = request.getParameter("title");
		
		String body = request.getParameter("body");
		
		String mo =  request.getParameter("mo");
		try {
			MenumanagementListDB DB = new MenumanagementListDB();
			DB.MenuUpdata(title, mkNum,body, mo, fileName);
			response.setContentType("text/html; charset=euc-kr"); //한글이 인코딩
			   PrintWriter out = response.getWriter(); //선언
			   
			   String str="";
			   str = "<script language='javascript'>";
			   str += "opener.location.reload();";  //오프너 새로고침
			   str += "self.close();";   // 창닫기
			    str += "</script>";
			   out.print(str);
			
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/Noticelist.jsp");
		//	dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
