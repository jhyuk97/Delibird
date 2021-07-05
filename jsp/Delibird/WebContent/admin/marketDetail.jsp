<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">



</head>
<body>
<br><br>
<br>
<form name="date" method="post" action="/Delibird/MarketDetailServlet">
<select name="year">

<%
    Calendar cal = Calendar.getInstance();
 
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    
    for(int i = year - 5; i < year + 5; i++) {
            out.println("<option>" + i + "</option>");
    }
    
%>
</select>년

<select name="month">
<%
    for(int i = 1; i <= 12; i++) {
            out.println("<option>" + i + "</option>");
    }
%>
</select>월
<input type="hidden" name="data" value="<%=request.getParameter("data") %>">
<input type="submit" name="go" value="검색">
</form>
<br>

<br>
<table class="detail" >
<colgroup>
	<col class = "number">
	<col class = "menu">
	<col class = "won">
	<col class = "date">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">No.</th>
		<th scope ="col" class = "menu">메뉴</th>
		<th scope ="col" class = "won">금액</th>
		<th scope ="col" class = "date">판매일</th>
	</tr>
</thead>
<tbody>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id ="conDB" class="Admin.MarketDetail" scope="request"/>
	<%
		String data = request.getParameter("data");
	ResultSet rs = (ResultSet)conDB.getRs(data);

		while(rs.next()){
			out.print("<tr>" + "<td>" + rs.getRow()+ "</td> <td>" + rs.getString(1) + "</td> <td>" + rs.getString(2) + "</td> <td>" + rs.getString(3) + "</td> </tr>");
		}
		conDB.closeDB();
	%>
</tbody>

</table>

</body>
</html>