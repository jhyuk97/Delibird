package Shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Shop.ShopDB;


/**
 * Servlet implementation class MarketApply_Servlet
 */


@WebServlet("/shop_Servlet")
public class shop_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shop_Servlet() {
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
		
		//String ID = request.getParameter("ID"); //iD
		String shopname = request.getParameter("shopname"); //�����̸�
		String shopapp = request.getParameter("shopapp"); // �����ּ� 
		String shopnumber = request.getParameter("shopnumber"); // ������ȭ��ȣ  
		String number = request.getParameter("number");
		
		
		try {
			ShopDB obj = new ShopDB();
			obj.getAddition(shopname,shopapp,shopnumber, number);
			//obj.getAddition(shoppw,number,Myname,iD);
				response.sendRedirect("shop/AchopkeeperMain.jsp");
				
			}catch(Exception e) {
				
				e.printStackTrace();
			
			}
	
	}
	
	}
	

