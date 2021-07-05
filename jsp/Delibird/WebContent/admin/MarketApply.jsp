
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  import="java.sql.*"  import="Admin.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<meta charset="UTF-8">
<title>매장신청 내역</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">

	<div class="inner" style = "padding: 0px 100px 0px 100px;">
         	<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
							
												<li><a href="MarketApply.jsp">매장 신청 내역	</a></li>
												<li><a href="market.jsp">매장 리스트</a></li>
									</ul>
								</header>
</div>

</head>

<body>	
		<div class="inner" style = "padding: 0px 100px 0px 100px;">
<script>
function showPopup() { window.open("MarketApplyOk.jsp", "a", "width=500, height=300,  "); }

$(function(){
	$('.delBtn').click(function(){
		var Btn = $(this);
		
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var Mname3 = td.eq(0).text();
		var realdata = {
				jsonData : JSON.stringify(Mname3)
		}
		
		$.post("/Delibird/MarketApply_Servlet", realdata, function(result){
			return result;
		})
		//delclass.action="/Delibird/MarketApply_Servlet";
		//delclass.method = "post";
		//delclass.submit();
	})
});

$(function(){
	$('.OK').click(function(){
		var Btn = $(this);
		
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var Mname3 = td.eq(0).text();
		//request.setAttribute("data",Mname3);
		
		window.open("MarketApplyOk.jsp?data="+ Mname3, "a", "width=700, height=500,  "); 
	})
});
</script>	

<%
	 request.setCharacterEncoding("UTF-8");

%>

<jsp:useBean id="conDB" class="Admin.MarketApplyConnectDB" scope="request" />


<% 		ResultSet rs = (ResultSet)conDB.getRs(); %>

<form id="delclass" name="delclass" method="POST" action="/Delibird/MarketApply_Servlet">

<table class="apply" >
<tr>
<td>신청인</td>
<td>매장이름</td>
<td>매장주소</td>
<td>업종</td>


<TD>승인</TD>
<TD>거절</TD>

</tr>

<%
// while(rs.next() ){
	 while(rs.next()){
%>
<tr>

<td><%=rs.getString("점주이름") %></td>
<td><%=rs.getString("매장이름") %></td>
<td><%=rs.getString("매장주소") %></td>
<td><%=rs.getString("매장타입") %></td>





<TD>
<input type="button" class="OK" value="수락"/>
</TD>


<TD>
    <input type="button" class="delBtn" value="거절"/>


</TD>
<!-- 여기서 부터 하기  -->


</tr>

<%
}
%>


<%
conDB.closeDB();
%>




</table>
</form>

</div>

</body>
</html>