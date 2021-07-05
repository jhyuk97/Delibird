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


@WebServlet("/AshopkeeperMemberFormServlet")
public class AshopkeeperMemberFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletRequest session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AshopkeeperMemberFormServlet() {
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
		
	 
		String id= request.getParameter("id");  //아이디
		String passwd = request.getParameter("passwd"); //비밀번호 
		String tel01 = request.getParameter("tel01");    //이름
		String phone02 = request.getParameter("phone02");  //핸드폰 번호
	
		String mkl01 = request.getParameter("mkl01");  //매장이름
		String mknum = request.getParameter("mknum"); //매장번호
		String mkes = request.getParameter("mkes");  //카테고리
		String sample4_roadAddress = request.getParameter("sample4_roadAddress"); 
		String sample4_jibunAddress = request.getParameter("sample4_jibunAddress"); 
	
		String sample4_detailAddress = request.getParameter("sample4_detailAddress"); 
		
		String email01= request.getParameter("email01"); 
		
		
		try {
			AshopkeeperMemberForm obj = new AshopkeeperMemberForm();
			
			
			obj.getAddition(id,passwd,tel01,phone02,mkl01,mknum,mkes,sample4_roadAddress,sample4_jibunAddress,sample4_detailAddress,email01);
		
			
				
			}catch(Exception e) {
				
				e.printStackTrace();
			
			}
		
		response.sendRedirect("shop/AshopkeeperLoginForm.jsp");
	
	}
	
	

}