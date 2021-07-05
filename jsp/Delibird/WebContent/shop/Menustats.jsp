<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"
import="java.sql.*"%>
<!DOCTYPE html>
<head>

<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<meta charset="utf-8"/>
<title>매장 메뉴 통계</title>
<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#startDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 maxDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                      //시작일(startDate) datepicker가 닫힐때
                      //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                     $("#endDate").datepicker( "option", "minDate", selectedDate );
                 }    
 
            });
            $( "#endDate" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 maxDate: 0,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                     // 종료일(endDate) datepicker가 닫힐때
                     // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                     $("#startDate").datepicker( "option", "maxDate", selectedDate );
                 }    
 
            });    
    });
</script>
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
										<li><a href="Statistics.jsp">매출 통계</a></li>
										<li><a href="Menustats.jsp">메뉴 통계</a></li>
                                
									</ul>
								</header>
								<br>


<form action="Menustats.jsp" method="get">
<div style="display:flex; flex-orientation:horizontal; justify-content:center">
<font size="5em">날짜를 선택하여 주세요 : &nbsp;&nbsp;&nbsp;&nbsp;</font>
<input type="text" id="startDate" name="StartDay" style="width:25%">&nbsp;&nbsp;<font size="5em">~</font> &nbsp;&nbsp;
 <input type="text" id="endDate" name="EndDay" style="width:25%">
&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="검색">
</div>
</form>
<%
	 request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="conDB" class="Shop.MenustatsDB" scope="request" />

<%
String ID = session.getAttribute("ID").toString();
if(request.getParameter("StartDay") != null && request.getParameter("EndDay") != null)
{
	ResultSet rs = (ResultSet)conDB.getRs(request.getParameter("StartDay"), request.getParameter("EndDay"), session.getAttribute("ID").toString());
%>
<table class="Menu">
<tr>
	<td>메뉴</td>
	<td>횟수</td>
</tr>
<%
	 while(rs.next()){
%>
<tr>

<td><%=rs.getString("메뉴명") %></td>
<td><%if(rs.getString("횟수") == null){ %>0 <%} else{  %> <%=rs.getString("횟수") %><%} %></td>
	<tr>

</tr>

<%

}
%>
</table>
<%
}else if(request.getParameter("StartDay") != null && request.getParameter("EndDay") == "")
	out.print("<script>alert('날짜를 입력해주세요');</script>");
else if(request.getParameter("StartDay") == "" && request.getParameter("EndDay") != null)
	out.print("<script>alert('날짜를 입력해주세요');</script>");
%>
<!--  날짜 간격을 선택하면 데이터 베이스에서 그사이에 주문 내역 내역을 가져옴  -->
		
</body>
</html>