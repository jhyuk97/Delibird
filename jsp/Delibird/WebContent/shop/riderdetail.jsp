<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.sql.*" import="Shop.*" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<head>
     <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<%
	 request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="conDB1" class="Shop.riderdetailConnectDB" scope="request" />


	<% 	
		ResultSet rs;
		if(request.getParameter("SearchName") != null){
			rs = (ResultSet)conDB1.Search(request.getParameter("SearchName"));
		}
		else{
			String test = session.getAttribute("ID").toString();
			rs =  (ResultSet)conDB1.getRs(session.getAttribute("ID").toString());
		}
	%>


		  <form id="" name="Search" method="POST" action="/Delibird/riderdetail_Servlet">

			
			라이더 ID :  <input type="text" name="SearchName" />
            <input type=submit  value="검색"  />
					

<table border=1 id="tr" class="apply" >
<tr>

<td>호출번호</td>
<td>매장번호</td>
<td>라이더ID</td>
<td>호출날짜</td>
<td>시간</td>


</tr>

<%
	 while(rs.next()){
%>
<tr>
<td><%=rs.getString("호출번호") %></td>
<td><%=rs.getString("매장번호") %></td>
<td><%=rs.getString("라이더ID") %></td>
<td><%=rs.getString("호출날짜") %></td>
<td><%=rs.getString("호출시간") %></td>




<!-- 여기서 부터 하기  -->


</tr>

<%
}
%>


<%
conDB1.closeDB();
%>




</table>
</form>
<script>
$(function(){
	$("#tr tr").click(function(){
		var tr = $(this);
		var td = tr.children();
		var Mname = td.eq(0).text();
		location.href="riderdetail_delete.jsp?data=" + Mname;
	})
});
</script>

</body>
</html>