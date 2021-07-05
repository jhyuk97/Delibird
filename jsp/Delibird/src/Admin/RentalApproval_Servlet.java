package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RentalApproval")
public class RentalApproval_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RentalApproval_Servlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn;
		PreparedStatement pstmt;
		String riderId = request.getParameter("riderId");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
			conn = DriverManager.getConnection(connectionUrl);

			String sql = "UPDATE 대여 SET 대여여부 = ? WHERE 라이더ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "대여신청");
			pstmt.setString(2, riderId);

			
			int result = pstmt.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			pw.write(String.valueOf(result));
			pw.flush();
			
			pw.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
