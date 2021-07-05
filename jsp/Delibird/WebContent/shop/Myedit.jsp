<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*"  import="Shop.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">


		<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
										<li><a href="Myedit.jsp">정보수정</a></li>
												<li><a href="Storeedit.jsp">매장 정보 수정</a></li>
                                
									</ul>
								</header>
	
	<title>점주 정보 수정</title>

	
	<%
	 request.setCharacterEncoding("UTF-8");

%>

<jsp:useBean id="conDB" class="Shop.StoreeditDB" scope="request" />


<% 		


ResultSet rs = (ResultSet)conDB.getRs(session.getAttribute("ID").toString()); %>

<form id="delclass" name="Storeedit" method="POST" action="/Delibird/Storeedit_Servlet">

<%

	 while(rs.next()){
%>

 
 	<table>
		 <tr>
		 <td>매장번호&nbsp;&nbsp;&nbsp;&nbsp; :</td>
		 <td><%=rs.getString("매장번호") %></td>
		 </tr>
		 
		 <tr>
		<td>ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</td>
 		<td><%=rs.getString("ID") %><input type="hidden" name="ID" value="<%=rs.getString("ID")%>"></td>
 		</tr>
 		
 		<tr>
 		<td>비밀번호&nbsp;&nbsp;&nbsp;&nbsp; :</td>
	  	<td><input type=text size=50  name=shoppw value=<%=rs.getString("PW") %>  ></td>
		</tr>
		
		<tr>
		<td>사업자번호 : &nbsp;&nbsp;</td>
		<td><input type=text name=number value=<%=rs.getString("사업자번호") %> > </td>
		</tr>
		
		<tr>
		<td>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</td>
		<td><input type=text name=Myname value= <%=rs.getString("이름") %> ></td>
		</tr>
		
		<tr>
	<td>전화번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</td>
	<td><input type=text  name=Callnumber  value= <%=rs.getString("전화번호") %> >	</td>
		</tr>

</table>
		<div align="center">
		<input type="button" value="뒤로가기" onclick="history.back(-1);">
		&nbsp;&nbsp;&nbsp;
		<input type= "submit"   value="수정하기" onclick="alert( 변경이 완료 되었습니다 );">
		</div>
		<% 
	 }
		%>
</Form>
</body>
</html>