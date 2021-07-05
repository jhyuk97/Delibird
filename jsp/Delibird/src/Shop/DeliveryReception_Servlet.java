package Shop;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Admin.NoticeDB;

/**
 * Servlet implementation class DeliveryReception_Servlet
 */
@WebServlet("/DeliveryReception_Servlet")
public class DeliveryReception_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryReception_Servlet() {
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
		 String button3;
		if(request.getParameter("act") == null) {
			button3 = request.getParameter("no");
		}
		else {
			button3 = request.getParameter("act");
		}
		 
		 if(button3.equals("거절")) {
			 String data = request.getParameter("num");
			 try {
				 DeliveryReception_DB DB = new DeliveryReception_DB();
				 DB.cancel(data);
				 response.sendRedirect("shop/ReceptionCheck.jsp");
			} catch (Exception e) {
				e.getMessage();
			}
		 }
		 else {
			 	String nu = request.getParameter("nu");
				String data = request.getParameter("num");
				String guide = request.getParameter("guide");
				
				try {
					DeliveryReception_DB DB = new DeliveryReception_DB();
					DB.Delete(nu,data,guide);
					response.sendRedirect("shop/ReceptionCheck.jsp");
				}catch(Exception e) {
					e.getMessage();
				}
		 }
		 
	
		 
		
	}
}
