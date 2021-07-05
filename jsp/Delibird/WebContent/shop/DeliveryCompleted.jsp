<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />
    <title>점주</title>
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
   
</head>
<body>





<h2>배달완료내역</h2>

<table border=1 width=500>
<tr><td>매장주소</td><td>고객주소</td><td>고객이름</td><td>전화번호</td></tr>

		<tr>
		<td><%=request.getParameter("address1") %></td>
		<td><%=request.getParameter("address2") %></td>
		<td><%=request.getParameter("name") %></td>
		<td><%=request.getParameter("Number1") %></td> 
		</tr>
</table>

 <form name = form1 method="post" action=DeliveryReception.jsp>
        
        <td align="center">
		<input type=submit style="height:50px; width:500px;" value="주문 보기"></td></tr>
<!--  폼안에 넣은 이유 는 나중에 데이터 베이스 이용할수도있을거같아서..?
 -->
 
</Form>
</center>


</body>
</html>