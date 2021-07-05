<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" import="Market.*" %>   
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매장 리스트</title>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">			
							
						
			<!-- Header -->
			<div class="inner" style = "padding: 0px 100px 0px 100px;">
							
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
							
												<li><a href="MarketApply.jsp">매장 신청 내역</a></li>
												<li><a href="market.jsp">매장 리스트</a></li>
									</ul>
									
								</header>
								
							
			</div>					
</head>
<body>


		<div class="inner" style = "padding: 0px 100px 0px 100px;">


<form action="market.jsp" method="get">





<input type ="text" name="search" style="width:92%; float:left" placeholder="매장 이름을 입력해주세요.">
<input type="submit" id="phone_num" value="검색" style="float:right">
</form>
<div align = "center">

<table id="trr" class = "market">

<colgroup>
	<col class = "number">
	<col class = "이름">
	<col class = "주소">
	<col class = "전화번호">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">No.</th>
		<th scope ="col" class = "이름">매장이름</th>
		<th scope ="col" class = "주소">주소</th>
		<th scope ="col" class = "전화번호">매장전화번호</th>
	</tr>
</thead>
<tbody>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="conDB" class="Admin.MarketListConnect" scope="request" />

<% 
// MarketListConnect conDB = (MarketListConnect)request.getAttribute("conDB");
String id = request.getParameter("search");
ResultSet rs = null;
if(id == null || id.equals("")){
	rs = (ResultSet)conDB.getRs();
}
else{
	rs = (ResultSet)conDB.getSearch(id);
}
%>

<%
while(rs.next()){
	

%>
	 <tr>
		<td><%=rs.getString("매장번호") %></td>
		<td><%=rs.getString("매장이름") %></td>
		<td><%=rs.getString("매장주소") %></td>
		<td><%=rs.getString("매장전화번호")%></td>
		
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
    		var Mname = td.eq(2).text();
    		var url = "marketDetail.jsp?data="+Mname;
            var name = "ReportDetail";
            var option = "width = 700, height = 600, top = 100, left = 200, location = no"
            window.open(url, name, option);
		})
	});
</script>
</div>
</body>
</html>