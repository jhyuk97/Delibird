<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"
import="Realreport.*"
import="Admin.*" %>   
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
 <title>문의 사항</title>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<title>Insert title here</title>
</head>
<body>
			<div class="inner" style = "padding: 0px 100px 0px 100px;">
			
			
<div align = "center">
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="conDB" class="Admin.RealreportDetailConnectDB" scope="request" />

<% 
String data = request.getParameter("data");
ResultSet rs = (ResultSet)conDB.getRs(data);
	

%>
<%
while(rs.next()){
	

%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<br>
<form id="re" name="answer" method="POST" action="/Delibird/RealreporttDetailServlet">
 <input name ="num" type ="hidden" value=<%=data%> >


<table style="height:150px"> 

<tr>
<th>문의번호 </th>
<th><%=rs.getString("문의번호")%></th>
</tr>

<tr>
<th>작성자 </th>
<th><%=rs.getString("작성자")%></th>
</tr>

<tr>
<th>분류 </th>
<th><%=rs.getString("분류")%></th>
</tr>

<tr>
<th>제목</th>
<th><%=rs.getString("제목")%></th>
</tr>


<tr>
<th>내용</th>
<th><%=rs.getString("내용")%></th>
</tr>
</table>
<input type="text" name="answer" style="height:200px;">
<br>
<input type = "submit" value = "답변하기" style="margin:auto" >


 
<%
	}
	
%>

<% 
	conDB.closeDB();
%>
</form>
</div>
</div>
</body>
</html>