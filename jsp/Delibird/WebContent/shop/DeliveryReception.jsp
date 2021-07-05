<%@ page language="java" contentType="text/html; charset=UTF-8"  import="java.sql.*" import="Shop.*" pageEncoding="UTF-8"%>

<%@page import="java.util.Calendar"%>



<head>
    <meta charset="utf-8"/>
	<title>주문 확인/취소</title>
    	<link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">


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
  											
  															<li><a href="ReceptionCheck.jsp">주문접수</a></li>
  											
  											  <li><a href="DeliveryWaiting.jsp">접수현황</a></li>
  									</ul>
  								</header>
	

	
	 <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data"); %>
  
   
<jsp:useBean id="conDB" class="Shop.DeliveryReception_DB" scope="request" />

<% ResultSet rs = (ResultSet)conDB.getRs(data);
   while(rs.next()){
%>
	
	 <form class="aaa" method="post" action="/Delibird/DeliveryReception_Servlet">
	
	  <input type="hidden" name="num" value="<%=data %>">
  
 
        <table border=5 cellspacing="1" cellpadding="10">
	<tr>
		<td align="center" >주문 번호 </td> <!-- address 1 매장 주소 라는 뜻   -->
		<td> <%=rs.getString("주문번호") %></td>
	</tr>
	
	<tr>
		<td align="center" >매장번호</td> <!-- address 1 매장 주소 라는 뜻   -->
		
			<td><%=rs.getString("매장번호")%></td>
			<input type="hidden" name="nu" value="<%=rs.getString("매장번호") %>"/>
		
	</tr>
	
	<tr>
		<td align="center" >사용자 ID </td><!-- address 2 고객 주소 라는 뜻   --> 
		<td> <%=rs.getString("사용자ID") %></td>
	</tr>
	
	
	<tr>
		<td align="center" >배송지</td>
		<td> <%=rs.getString("배송지") %></td>
	</tr>
	
	
	<tr>
		<td align="center" >주문현황</td>
		<td> <%=rs.getString("요청사항") %></td>
	</tr>
	

	
	<tr>
		<td align="center">금액</td>
		<td> <%=rs.getString("금액") %></td>
	</tr>
	
	<tr>
		<td align="center">현황</td>
		<td><%=rs.getString("현재상황") %></td>
		<input type="hidden" name="present" value="<%=rs.getString("현재상황") %>"/>
	</tr>

		<tr>
		<td align="center">배달안내</td>
		<td> 	<input type=text  name=guide  value=<%=rs.getString("배달안내") %> ></td>
	</tr>
			 
		</table>
		<div  style="display:flex; flex-orientation:horizontal; justify-content:center">
                <input type="submit" name = "act" value="라이더 호출 " >
                &nbsp;&nbsp;&nbsp;
                <input type="submit" name="no" value="거절"> 
         </div>
         <br>
			<%
   }
			
			%>
	<% 
	conDB.closeDB();
%>
		</form>

</body>
</html>