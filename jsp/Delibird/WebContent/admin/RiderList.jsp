<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<meta charset="UTF-8">
<title>라이더 목록</title>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<body class="is-preload">

				
						<div class="inner" style = "padding: 0px 100px 0px 100px;" >

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

	<div class="inner" style = "padding: 0px 100px 0px 100px;" >
	

</head>
<body>

<form action="RiderList.jsp" method="get">
<input type="text" name="name" style="width:94%; float:left" placeholder="라이더 아이디를 입력해주세요.">
<input type="submit" value="검색" style="float:right">
</form>
<jsp:useBean class="Admin.RiderListDB" id="DB" scope="request"/>
<% request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");
ResultSet rs = (ResultSet)DB.getRs(name);
%>
<br>
<table id="tr" class="riderlist">
<colgroup>
	<col class = "number">
	<col class = "riderid">
	<col class = "ridername">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">No.</th>
		<th scope ="col" class = "user">라이더아이디</th>
		<th scope ="col" class = "category">이름</th>
	</tr>
</thead>
<tbody>
<% int i = 1;
while(rs.next()){ 
%>
	<tr>
		<td><%=i %></td>
		<td><%=rs.getString("ID") %></td>
		<td><%=rs.getString("이름") %></td>
	</tr>
<%i++;} 
DB.closeDB();
%>
</tbody>
</table>
<script>
	$(function(){
		$("#tr tr").click(function(){
			var tr = $(this);
    		var td = tr.children();
    		var Rname = td.eq(1).text();
    		location.href = "RiderDetail.jsp?ID="+Rname;
		})
	});
</script>
</body>
</html>