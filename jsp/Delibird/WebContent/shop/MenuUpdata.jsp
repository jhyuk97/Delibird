<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.sql.*" import="Shop.*" %>
<!DOCTYPE html5>

<head>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<title>메뉴 추가</title>

		<!-- Wrapper -->
			<div id="wrapper">
				<!-- Main -->
					<div id="main">
						<div class="inner">
							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
											
									</ul>
								</header>
    <%! 
    	String 이름, ID, PW;
    %>
    
    <%
		session.getAttribute("ID");
 	   %>
 	   
</head>
    
 
  	<%
	 request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="conDB" class="Shop.MenumanagementListDB" scope="request" />


<% 		


ResultSet rs2 = (ResultSet)conDB.getRs2(session.getAttribute("ID").toString()); 

%>


	
	
 	<meta charset="utf-8"/>
    <form class="NumeUpdata" method="POST" action="/Delibird/MenuUpdataServlet"> 
    
    <table>
 	
 	  <%while(rs2.next()){ %>  
 	  <tr>
      	<td align="center">ID</td>
      	<td><%=session.getAttribute("ID") %></td>
      	<inpute type="hidden" name ="ID" value = "<%=session.getAttribute("ID") %>"/>
      </tr>
      
      <tr>      	
      	<td align="center">매장번호</td>
      	<td><%=rs2.getString("매장번호") %></td>
      	<input type="hidden" name="mkNum" value = "<%=rs2.getString("매장번호") %>"/>
	</tr>
      

      <tr>  
      	<td align="center">메뉴번호</td>
     	<td><input type="Text" name="title"></td>
      </tr>
     
      <tr>
      	<td align="center">메뉴이름</td>
        <td><input type="Text" name="body"></td>
      </tr>
      
      <tr>
      <td align="center">사진</td>
      <td><input type="file" name="photo"></td>
      </tr>
     
      <tr>
      	<td align="center">가격</td>
        <td><input type="Text" name="mo"></td>
     </tr>
	<tr align="center">

      <td colspan="2"><input type=submit value="등록">
       &nbsp;&nbsp;&nbsp;
      <input type=button value="취소" onClick = "GoBack();">

     </tr>

<%} %>
    </table>
</form>  

<script>      
function GoBack(){
        	location.href="Menumanagement.jsp";
        }
</script>

</body>
</html>
