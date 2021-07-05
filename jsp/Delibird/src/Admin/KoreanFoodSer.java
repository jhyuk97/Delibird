package Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KoreanFoodSer
 */
@WebServlet("/KoreanFoodSer")
public class KoreanFoodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KoreanFoodSer() {
        super();
        // TODO Auto-generated constructor stub
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
		String photo = request.getParameter("Photo");
		String title = request.getParameter("title");
		String foodname = request.getParameter("FoodName");
		String detail = request.getParameter("detail");
		String fileName = "..\\IMG\\" + photo.substring( photo.lastIndexOf("\\")+1, photo.length() );
		String andImg = "/storage/emulated/0/DCIM/Image/" + photo.substring( photo.lastIndexOf("\\")+1, photo.length());
		try {
			KoreanFoodDB DB = new KoreanFoodDB();
			DB.InsertData(fileName, title, detail, foodname, andImg);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("admin/KoreanFoodList.jsp");
	}

}
