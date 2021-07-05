<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
import="java.sql.*"%>
<!DOCTYPE html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>

  function showPopup() { window.open("TimePopup.jsp", "a", "width=500, height=300,  "); }
</script>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
    <meta charset="utf-8"/>
    <meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />

   
    <body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
								
								</header>

	
	 <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data"); %>
   
<jsp:useBean id="conDB" class="Shop.riderdetail_delete_DB" scope="request" />

<% ResultSet rs = (ResultSet)conDB.getRs(data);
   while(rs.next()){
%>
	
	<h1 align="center" >라이더 내역 상세보기   </h1>
	 <form class="aaa" method="post" action="/Delibird/riderdetail_delete_Servlet">
	
	  <input type="hidden" name="num" value="<%=data %>">
  
 
        <table border=5 cellspacing="1" cellpadding="10">
	<tr>
		<td align="center" >호출번호 </td> <!-- address 1 매장 주소 라는 뜻   -->
		<td> <%=rs.getString("호출번호") %></td>
	</tr>
	
	<tr>
		<td align="center" >매장번호</td> <!-- address 1 매장 주소 라는 뜻   -->
		<td> <%=rs.getString("매장번호") %> </td>
	</tr>
	
	<tr>
		<td align="center" >라이더ID</td><!-- address 2 고객 주소 라는 뜻   --> 
		<td> <%=rs.getString("라이더ID") %></td>
	</tr>
	
	<tr>
		<td align="center" >호출날짜</td>
		<td> <%=rs.getString("호출날짜") %></td>
	</tr>
	

	
	<tr>
		<td align="center">시간</td>
		<td> <%=rs.getString("호출시간") %></td>
	</tr>
	
  <td colspan="2"><input type="submit" value="삭제">
			
		</table>
			<%
   }
			
			%>
	
		</form>


</body>
</html>