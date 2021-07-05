<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한식 리스트</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
					
								<div class="inner" style = "padding: 0px 100px 0px 100px;" >	

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
   	<div class="inner" style = "padding: 0px 100px 0px 100px;" >	
<jsp:useBean id="conDB" class="Admin.koreanFoodListDB" scope="request" />
<% ResultSet rs = (ResultSet)conDB.getRs(); %>
<table id="koreanFoodList" class="koreanFoodList">

<colgroup>
	<col class = "img">
	<col class = "imgname">
	<col class = "imggoo">
</colgroup>

<thead>
	<tr>
	<th scope ="col" class = "NO.">No.</th>
		<th scope ="col" class = "img">제목</th>
		<th scope ="col" class = "imgname">사진이름</th>
		<th scope ="col" class = "imggoo">사진설명</th>
	</tr>
</thead>
<tbody>
<%
	 while(rs.next()){
%>
<tr>
<td><%=rs.getString("홍보번호") %></td>
<td><%=rs.getString("제목") %></td>
<td><%=rs.getString("사진이름") %></td>
<td><%=rs.getString("사진설명") %></td>
</tr>

<%

}
conDB.closeDB();
%>

</tbody>
</table>
<input type="button" name="write"  style="float:right" value="홍보게시판등록" onClick="GoTo()"/>
<br><br><br><br><br>

<script>
$(function(){
	
	$("#koreanFoodList tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var number = td.eq(0).text();
        location.href="KoreanFoodD.jsp?data="+number;
	})
});
function GoTo(){
	location.href = "KoreanFood.jsp";
}
</script>
</div>
</body>
</html>