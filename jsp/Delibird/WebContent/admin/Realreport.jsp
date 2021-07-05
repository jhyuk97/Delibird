<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%@ page import="java.sql.*"
import="Realreport.*"
import="Admin.*" %>   
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
 <title>문의 사항 내역</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
	
			<div class="inner" style = "padding: 0px 100px 0px 100px;">
	<!-- Header -->
	

								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
								</header>
	
	</div>
	
	</head>
		<div class="inner" style = "padding: 0px 100px 0px 100px;">
<table id="trr" class = "Realreport">
<colgroup>
	<col class = "num">
	<col class = "작성자">
	<col class = "분류">
	<col class = "제목">
	<col class = "내용">
	<col class = "답변">
	
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "num">No.</th>
		<th scope ="col" class = "name">작성자</th>
		<th scope ="col" class = "분류">분류</th>
		<th scope ="col" class = "제목">제목</th>
			<th scope ="col" class = "내용">내용</th>
		<th scope ="col" class = "답변">답변</th>
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
<script>

	$(function(){
	
		$("#trr tr").click(function(){
			var tr = $(this);
    		var td = tr.children();
    		var Mname = td.eq(0).text();
    		var url = "ReportDetail.jsp?data="+Mname;
            var name = "ReportDetail";
            var option = "width = 750, height = 800, top = 200, left = 200, location = no"
            window.open(url, name, option);
		})
	});
</script>
</div>
</body>
</html>