<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="java.sql.*" import="Shop.*" %>
<!DOCTYPE html5>

<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <meta charset="utf-8"/>
    <title>메뉴 관리</title>
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">


  		<!-- Wrapper -->
  			<div id="wrapper">

  				<!-- Main -->
  					<div id="main">	
  						<div class="inner">

  							<!-- Header -->
  								<header id="header">
  									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>

  								</header>

		



 <table border=1 id="Menum" class="Menumanagement">
<jsp:useBean id="conDB" class="Shop.MenumanagementListDB" scope="request" />
<% 	

ResultSet rs = (ResultSet)conDB.getRs(session.getAttribute("ID").toString()); %>
<colgroup>
	<col class = "number">
	<col class = "title">
	<col class = "date">
</colgroup>

<thead>
	<tr>
		<th  class = "number">매장번호</th>
		<th  class = "title">메뉴번호</th>
		<th  class = "date">메뉴명</th>
	</tr>
</thead>
<tbody>
<%
	 while(rs.next()){
%>


<tr>
<td><%=rs.getString("매장번호") %></td>
<td><%=rs.getString("메뉴번호") %></td>
<td><%=rs.getString("메뉴명") %></td>
</tr>

<%

}
conDB.closeDB();
%>

</tbody>

</table>


<script>
$(function(){
	
	$("#Menum tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var title = td.eq(1).text();
		 var name = "MenuD";
		 var option = "width = 750, height = 400, top = 200, left = 200, location = no"
		window.open("MeunD.jsp?data="+title, name, option);
	})
});




$(function(){
	$('.MenuUpdata').click(function(){
		var Btn = $(this);
		
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var Mname3 = td.eq(0).text();
		
	
		window.open("MenuUpdata.jsp?data="+number ); 
	})
});



</script>

<td><input type="button" style="float:right" size="30" name="MenuUpdata" value="추가" onClick="showPopup()"/></td>


<script  language="javascript">
function showPopup() {
	
	var windowW = 500;  // 창의 가로 길이
    var windowH = 450;  // 창의 세로 길이
    var left = Math.ceil((window.screen.width - windowW)/2);
    var top = Math.ceil((window.screen.height - windowH)/2);



	
	window.open("MenuUpdata.jsp","pop_01","l top="+top+", left="+left+", height="+windowH+", width="+windowW);

	
	
	
	
	}


</script>
</body>
</html>