<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<head>
    <meta charset="utf-8"/>
    <title>한식 추가</title>
	<link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
	
								<div class="inner" style = "padding: 0px 300px 0px 300px;" >	

	<!-- Header -->
	
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
										<li><a href="#" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
										<li><a href="#" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
										<li><a href="#" class="icon brands fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
										<li><a href="#" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
										<li><a href="#" class="icon brands fa-medium-m"><span class="label">Medium</span></a></li>
									</ul>
									
								</header>
								</div>
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
	<div class="inner" style = "padding: 0px 300px 0px 300px;" >	
<div align="center">
	<form action="/Delibird/KoreanFoodSer" method="post">
	<table border="1">
	<tr>
			<td align="center" >제목</td>
		<td><input type="text" name="title"></td>
	<tr>
		<td align="center" >음식 이름</td> 
		<td><input type=text size=20 name=FoodName ></td>
	</tr>
	
	<tr>
		<td align="center" >사진</td>
		
		<td><input type="file" name="Photo"></td>
	</tr>	
	<tr>
		<td align="center" >음식설명</td>
		<td><input type="text" name="detail"></td>
	

	<tr>
	<td colspan=2 align="center">
		<input type=submit style="height:50px; width:150px;" value="등록"></td>
	</tr>

	</table>
	</form>
	</div>
</div>
</body>
</html>