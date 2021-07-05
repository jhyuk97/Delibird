<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.*" import="Manager.*" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script
 src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">


</script>


<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<body>
	<body class="is-preload">
	<div class="inner" style = "padding: 0px 100px 0px 100px;">
	<title>라이더 대여 신청 내역</title>
		

		

							<!-- Header -->
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
							
												<li><a href="./RiderList.jsp">라이더 목록</a></li>
												<li><a href="./Rental.jsp">대여 신청</a></li>
												<li><a href="./RentalL.jsp">대여 내역</a></li>
									</ul>
								</header>
								
								


</div>

</head>
		<div class="inner" style = "padding: 0px 100px 0px 100px;">

<table id="Rental" class="Rental">


<thead>
	<tr>
		<th scope ="col" class = "ID">라이더아이디</th>
		<th scope ="col" class = "Type">번호판</th>
		<th scope ="col" class = "date">대여신청</th>
		<th scope ="col" class = "ok">대여일</th>
	
	</tr>
</thead>

<tbody>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="conDB" class="Admin.RentalConnectList" scope="request" />

<% 
// MarketListConnect conDB = (MarketListConnect)request.getAttribute("conDB");
	ResultSet rs = (ResultSet)conDB.getRs();

%>


<%
while(rs.next()){
	

%>
	<tr>
		<td><%=rs.getString("라이더ID")%></td>
		<td><%=rs.getString("번호판") %></td>
		<td><%=rs.getString("대여신청") %></td>
		<td><%=rs.getString("대여일") %></td>
	</tr>

<%
	}
	
%>

</tbody>

</table>
<div style="float:right">
오토바이 대여 신청서PDF :<a  href= "Rental.pdf" download >오토바이 대여신청서</a>

</div>
</div>


<script>
$(function(){
	
	$("#Rental tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var number = td.eq(0).text();
        location.href="RentalD.jsp?data="+number;
	})
});

</script>


<% 
	conDB.closeDB();
%>

</body>

</html>