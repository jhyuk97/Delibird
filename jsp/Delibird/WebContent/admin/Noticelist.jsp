<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <meta charset="utf-8"/>
    <title>공지사항 목록</title>
    <meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />
    
    
    
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">

<meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />
	
			<!-- Header -->
						<div class="inner" style = "padding: 0px 100px 0px 100px;">

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
<jsp:useBean id="conDB" class="Admin.NoticeListDB" scope="request" />
<% ResultSet rs = (ResultSet)conDB.getRs(); %>
<table id="Notice" class="noticelist">
<colgroup>
	<col class = "number">
	<col class = "title">
	<col class = "date">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">No.</th>
		<th scope ="col" class = "title">제목</th>
		<th scope ="col" class = "date">등록일</th>
	</tr>
</thead>
<tbody>
<%
	 while(rs.next()){
%>
<tr>
<td><%=rs.getString("공지번호") %></td>
<td><%=rs.getString("제목") %></td>
<td><%=rs.getString("날짜") %></td>
</tr>

<%

}
conDB.closeDB();
%>
</tbody>
</table>

<input type="button" name="write" value="글쓰기" style="float:right" onClick="GoTo()"/>
<script>
$(function(){
	
	$("#Notice tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var number = td.eq(0).text();
		//var url = "Notice.jsp?data="+number;
        //var name = "ReportDetail";
        //var option = "width = 700, height = 600, top = 100, left = 200, location = no"
        //window.open(url, name, option);
        location.href="Notice.jsp?data="+number;
	})
});
function GoTo(){
	location.href = "Write.jsp";
}
</script>
</div>
</body>
</html>