<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />
   <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
	<title>대여 승인</title>
	<div class="inner" style = "padding: 0px 100px 0px 100px;">
                     
                        <header id="header">
                           <a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
                        </header>
                        
                     
         </div>

</head>
<body>

     <form class="RentalDServlet" method="post" action="/Delibird/RentalDServlet">
   <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data"); %>
<jsp:useBean id="conDB" class="Admin.RentalDDB" scope="request" />
<% ResultSet rs = (ResultSet)conDB.getRs(data);
   while(rs.next()){
%>

   <table style="width:85%; margin:auto" border=5 cellspacing="1" cellpadding="10">
	<tr>
		<td align="center" >라이더ID</td> <!-- address 1 매장 주소 라는 뜻   -->
		<td><%=data %><input type=hidden  name="data" value="<%=data%>">
  </td>
	</tr>
	
	<tr>
		<td align="center" >번호판</td> <!-- address 1 매장 주소 라는 뜻   -->
		
		<td>	<input type=text  name=rinumber value=<%=rs.getString("번호판") %>></td>
		
	</tr>
	
	<tr>
		<td align="center" >대여일 </td><!-- address 2 고객 주소 라는 뜻   --> 
	<td>	<input type=text  name=riday value=<%=rs.getString("대여일") %>></td>
	</tr>
	

		<tr>
		<td align="center" >오토바이 기종  </td><!-- address 2 고객 주소 라는 뜻   --> 
	<td>	<input type=text  name=riw ></td>
	</tr>
	
		

			 
			
		</table>
			<%
   }
			
			%>
	<% 
	conDB.closeDB();
%>
	<br>
	<div style="display:flex; flex-orientation:horizontal; justify-content:center">
	
   <input type="submit"value="등록하기" style="margin:auto" onclick="alert( 등록이 완료되었습니다);">
	</div>
	
</body>

</html>