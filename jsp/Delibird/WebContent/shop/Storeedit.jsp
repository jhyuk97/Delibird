<%@ page language="java" contentType="text/html; charset=UTF-8"  import="java.sql.*"  import="Shop.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>매장 정보 수정</title>
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">

	
		<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
										<li><a href="Myedit.jsp">정보수정</a></li>
												<li><a href="Storeedit.jsp">매장 정보 수정</a></li>
                                
									</ul>
								</header>
	
	

		<%
	 request.setCharacterEncoding("UTF-8");

%>



<jsp:useBean id="conDB" class="Shop.ShopDB" scope="request" />


<% 	

ResultSet rs = (ResultSet)conDB.getRs(session.getAttribute("ID").toString()); %>






<form id="shopee" name="shopee" method="POST" action="/Delibird/shop_Servlet">

<%

	 while(rs.next()){
%>
  <table>
  
  <tr>
	<td align="center" >매장번호</td>
		<td> <%=rs.getString("매장번호") %><input type="hidden" name="number" value="<%=rs.getString("매장번호") %>"></td>
		
	</tr>
		
<td align="center" >매장이름</td>
	<td><input type=text  name=shopname value=<%=rs.getString("매장이름") %> > </td>
	
	</tr>
	
	<tr>
		<td align="center" >매장주소</td>
		<td><input type=text  name=shopapp value=<%=rs.getString("매장주소") %>></td>
	</tr>
	<tr>
		<td align="center">매장전화번호</td>
		<td><input type=text name=shopnumber value=<%=rs.getString("매장전화번호")%>></td>
	</tr>
	<tr>
	<td colspan=2 align="center">
	
		<input type="button" value="뒤로가기" onclick="history.back(-1);">
		&nbsp;&nbsp;&nbsp;
		<input type= "submit"   value="수정하기">
	</table>	
	
		
		


		<% 
	 }
		%>
			
</Form>
</body>
</html>