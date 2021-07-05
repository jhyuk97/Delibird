<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.sql.*" import="Shop.*" %>
<!DOCTYPE html5>

<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <meta charset="UTF-8"/>
    <title>메뉴 정보</title>

       <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
</head>
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
								
								
<body>
<center> <!-- 가운대 정렬  -->

	
  <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data");
   String  number = request.getParameter("number");
   
   
   %>
<jsp:useBean id="conDB" class="Shop.MenuDB" scope="request" />



<% //esultSet rs = (ResultSet)conDB.getRs(data,number);

ResultSet rs = (ResultSet)conDB.getRs(data,session.getAttribute("ID").toString()); 



   while(rs.next()){
%>


  <form class="Menu" method="post" action="/Delibird/MenuDB_Servlet">
<input type="hidden" name="num" value="<%=data%>"> 
   <table id="Menum" >
     <tr>
      <td align="center">매장번호&nbsp;&nbsp; : &nbsp;&nbsp;</td>
      <td><%=rs.getString("매장번호") %></td>
 </tr>
     <tr>
        <td align="center">메뉴번호&nbsp;&nbsp; : &nbsp;&nbsp;</td>
 		<td><%=rs.getString("메뉴번호") %> </td>
	 </tr> 
	<tr>
      <td align="center">메뉴이름&nbsp;&nbsp; : &nbsp;&nbsp;</td>
      <td><%=rs.getString("메뉴명") %> </td> 
    </tr>

     
     
     
       <%
      }

      conDB.closeDB();
      %>
    </table>
    <input type="submit" style="float:right; height:40px; width:100px;"  value="삭제 ">
     </form>
      <input name ="num" type ="hidden" >
     
     <script>


/*$(function(){
	$('.delBtn').click(function(){
		var Btn = $(this);
		
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var Mname3 = td.eq(1).text();
	
		var realdata = {
				jsonData : JSON.stringify(Mname3)
		}
		
		$.post("/Delibird/MenuChang_Servlet", realdata, function(result){
			return result;
		})
	})
});*/
</script>
    

</body>
</html>