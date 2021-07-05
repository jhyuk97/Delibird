package Shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shop.StoreeditDB;


/**
 * Servlet implementation class MarketApply_Servlet
 */
@SuppressWarnings("unused")


@WebServlet("/Storeedit_Servlet")
public class Storeedit_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletRequest session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Storeedit_Servlet() {
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
		
		String ID = request.getParameter("ID");
		String shoppw = request.getParameter("shoppw"); //��й�ȣ
		String number = request.getParameter("number"); // ����ڹ�ȣ 
		String Myname = request.getParameter("Myname"); // �̸�  
		String Callnumber = request.getParameter("Callnumber"); // �����ȣ  
	


		try {
				StoreeditDB obj = new StoreeditDB();
				obj.getAddition(shoppw,number,Myname,Callnumber,ID);
				response.sendRedirect("shop/AchopkeeperMain.jsp");
				
			}catch(Exception e) {
				
				e.printStackTrace();
			
			}
	
	}
	
	

}