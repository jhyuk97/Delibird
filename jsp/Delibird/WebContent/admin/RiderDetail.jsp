<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
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
                 dateFormat: "yy-mm-dd",
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
                 dateFormat: "yy-mm-dd",
                 maxDate: 0,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                     // 종료일(endDate) datepicker가 닫힐때
                     // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                     $("#startDate").datepicker( "option", "maxDate", selectedDate );
                 }    
 
            });    
    });
</script>
<meta charset="UTF-8">
<title>라이더 배달 내역</title>
</head>
<body>
<br>
<br>

<form name="ridercode" action="RiderDetail.jsp" method="get">
<div style="display:flex; flex-orientation:horizontal; justify-content:center">
<font size="5em">날짜를 선택하여 주세요&nbsp;&nbsp;:</font>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" id="startDate" name="StartDay" style="width:27%">&nbsp;&nbsp; <font size="5em">~ </font> &nbsp; &nbsp;
<input type="text" id="endDate" name="EndDay" style="width:27%">
&nbsp;&nbsp; &nbsp;&nbsp; 
<input type="hidden" name ="ID" value = <%=request.getParameter("ID") %>>
<input type="submit" value="검색">
</div>
</form>
<jsp:useBean id="DB" class="Admin.RiderDetailDB" scope="request"/>
<%request.setCharacterEncoding("UTF-8");
String ID = request.getParameter("ID");
String day1 = request.getParameter("StartDay");
String day2 = request.getParameter("EndDay");
ResultSet rs = (ResultSet)DB.getRs(ID,day1,day2);
%>

<table class="riderDetail" style="width:83%; margin:auto">
<colgroup>
	<col class = "number">
	<col class = "user">
	<col class = "date">
	<col class = "pay">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">No.</th>
		<th scope ="col" class = "user">라이더아이디</th>
		<th scope ="col" class = "date">날짜</th>
		<th scope ="col" class = "pay">금액</th>

	</tr>
</thead>
<tbody>
<%int i = 1;
while(rs.next()){ 
%>
	<tr>
		<td><%=i %></td>
		<td><%=rs.getString("라이더ID") %></td>
		<td><%=rs.getString("호출날짜") %></td>
		<td>2000</td>
	</tr>
	<%i++;}
DB.closeDB();%>
</tbody>
</table>
	
</body>
</html>