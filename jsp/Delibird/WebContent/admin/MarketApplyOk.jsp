<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"
    pageEncoding="UTF-8"%>
    
    
 

    <% request.setCharacterEncoding("UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<title>매장 상세 정보</title>
<meta charset="UTF-8">

<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">


</head>
<body>
		<div class="inner" style = "padding: 0px 100px 0px 100px;">
<div align="center">
<jsp:useBean id ="DB" class="Admin.MarketApplyConnectDB" scope="request"/>
<%
		String data = request.getParameter("data");
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	Statement stmt =null;
    	ResultSet rs = null;
    	try{
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // SQL 서버
    		String connectionUrl = "jdbc:sqlserver://localhost:1433; DataBaseName=DeilBird; user=sa; password=system;";
    		conn = DriverManager.getConnection(connectionUrl);
    	}catch(Exception e){
    		e.getMessage();
    	}
		rs = DB.Apply(data);
    	%>

<form name=form1 method=post action=MarketApplyOk.jsp>
<%while(rs.next()){ %>
<table>
<tr>
<td>매장번호 : </td>
<td><%=rs.getString("매장번호") %></td>
</tr>
<tr>
<td>매장이름 : </td>
<td><%=rs.getString("매장이름") %></td>
</tr>

<tr>
<td>매장주소 : </td>
<td><%=rs.getString("매장주소") %></td>
</tr>

<tr>
<td>매장전화번호  : </td>
<Td><%=rs.getString("매장전화번호") %></Td>
</tr>
</table>
<input type=submit value="등록" onClick='self.close()'  >
<%} 
try{
	stmt = conn.createStatement();
	String sql = "select * from 매장신청 where 점주이름 ='" + data + "'";
	rs = stmt.executeQuery(sql);
	String a = "",b = "",c = "",d = "",e = "";
	while(rs.next()){
		a = rs.getString("매장번호");
		b = rs.getString("매장이름");
		c = rs.getString("매장주소");
		d = rs.getString("매장전화번호");
		e = rs.getString("매장타입");
	}
	
	sql = "insert into 매장(매장번호,매장이름,매장주소,매장전화번호,카테고리) values('" + a + "', '" + b + "', '" + c + "','" + d + "','"+ e +"')";
	pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	
	sql = "delete 매장신청 where 점주이름 = '" + data +"'";
	pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();

	
} catch(Exception e){
	out.println("연결 실패");
	e.getMessage();
}

DB.closeDB();
%>

</form>
</div>
</div>
</body>
</html>