<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
import="java.sql.*"%>
<!DOCTYPE html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js">
function showPopup() { window.open("TimePopup.jsp", "a", "width=500, height=300,  "); }
</script>
  <meta charset="utf-8"/>
  <link rel="stylesheet" href="Design/resource/assets/css/main.css">
  <link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
  <title>주문 접수 현황</title>


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
<jsp:useBean id="DB" class="Shop.ReceptionCheckDB" scope="request"/>
<% 		
ResultSet rs = (ResultSet)DB.getRs(session.getAttribute("ID").toString()); %>
<div align="center">

<table id="tr">
	<tr>
		<td>주문번호</td>
		<td>배송지</td>
		<td>메뉴</td>
	</tr>
<%while(rs.next()){ %>
	<tr>
		<td><%=rs.getString("주문번호") %></td>
		<td><%=rs.getString("배송지") %></td>
		<td><%=rs.getString("메뉴") %></td>
	</tr>
<%} %>
</table>

</div>
<script>
$(function(){
	$("#tr tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var Mname = td.eq(0).text();
		location.href="DeliveryReception.jsp?data=" + Mname;
	})
});
</script>

</body>
</html>