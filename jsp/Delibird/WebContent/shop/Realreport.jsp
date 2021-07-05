<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ page import="java.sql.*"
import="Realreport.*"
import="Admin.*" %>   
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
 <title>문의 사항</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<title>문의 사항</title>

</head>
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
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
								</header>

<div class="table-wrapper">
<table id="trr" class = "Realreport">

<thead>
	<tr>
		<th class = "num">No.</th>
		<th class = "name">작성자</th>
		<th class = "분류">분류</th>
		<th class = "제목">제목</th>
		<th class = "내용">내용</th>
		<th class = "답변">답변</th>
	</tr>
</thead>
<tbody>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="conDB" class="Admin.RealreportConnect" scope="request" />

<% 

	ResultSet rs = (ResultSet)conDB.getRs();

%>

<%
while(rs.next()){
	

%>
	 <tr>
		<td><%=rs.getString("문의번호") %></td>
		<td><%=rs.getString("작성자") %></td>
		<td><%=rs.getString("분류") %></td>
		<td><%=rs.getString("제목")%></td>
			<td><%=rs.getString("내용")%></td>
				<td><%=rs.getString("답변")%></td>
				
	</tr>
<%
	}
	
%>

<% 
	conDB.closeDB();
%>

</tbody>
</table>
</div>
				
<script>

	$(function(){
	
		$("#trr tr").click(function(){
			var tr = $(this);
    		var td = tr.children();
    		var Mname = td.eq(0).text();
    		var url = "ReportDetail.jsp?data="+Mname;
            var name = "ReportDetail";
            var option = "width = 750, height = 500, top = 200, left = 200, location = no"
            window.open(url, name, option);
		})
	});
</script>
			<script src="./Design/resource/assets/js/jquery.min.js"></script>
			<script src="./Design/resource/assets/js/main.js"></script>
			<script src="./Design/resource/assets/js/browser.min.js"></script>
			<script src="./Design/resource/assets/js/breakpoints.min.js"></script>
			<script src="./Design/resource/assets/js/util.js"></script>	
	</div>
</body>
</html>