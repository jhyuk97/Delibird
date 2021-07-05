<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<meta charset="EUC-KR">
<title>주문 접수 완료 내역</title>


<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
														<li><a href="ReceptionCheck.jsp">주문접수</a></li>
  												
  											  <li><a href="DeliveryWaiting.jsp">주문내역</a></li>
									</ul>
								</header>



<%
	 request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="DB" class="Shop.DeliveryWaiting" scope="request"/>
<% 		
ResultSet rs = (ResultSet)DB.getRs(session.getAttribute("ID").toString()); %>
<div align = "center">

<table>
	<tr>
		<td>주문번호</td>
		<td>주소</td>
		<td>메뉴</td>
		<td>금액</td>
		<td>현재상황</td>
		<td>주문 시간</td>
	</tr>
	<%
	while(rs.next()){
		%>
	<tr>
		<td><%=rs.getString("주문번호") %></td>
		<td><%=rs.getString("배송지") %></td>
		<td><%=rs.getString("메뉴") %></td>
		<td><%=rs.getString("금액") %></td>
		<td><%=rs.getString("현재상황") %></td>
		<td><%=rs.getString("호출시간") %></td>
	</tr>
<%} %>
</table>
</div>
</body>
</html>