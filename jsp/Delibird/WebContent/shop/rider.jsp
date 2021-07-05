<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<head>
<title>라이더</title>
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
  <script language="javascript">
  function showPopup() { window.open("popup.html", "a", "width=350, height=200,  "); }
  </script>
    <title>점주</title>
    	<link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
    
</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
							
								</header>
								
							
							
</head>


<center>
	<h1 align="center" >라이더호출  </h1>
        <table border=5 cellspacing="1" cellpadding="10">
	<tr>
		<td align="center" >매장 위치</td> <!-- address 1 매장 주소 라는 뜻   -->
		<td><input type=text size=50 name=address1 ></td>
	</tr>
	
	<tr>
		<td align="center" >고객 주소</td><!-- address 2 고객 주소 라는 뜻   --> 
		<td><input type=text size=50 name=address2></td>
	</tr>
	
	<tr>
		<td align="center" >고객이름</td>
		<td><input type=text size=20 name=name></td>
	</tr>
	<tr>
		<td align="center">메뉴</td>
		<td><input type=text size= name=Mune></td>
	</tr>
	<tr>
	<td colspan=2 align="center">
		<input type=button  onclick="showPopup();" style="height:50px; width:400px;" value="라이더호출하기"></td></tr>
			<!--   데이터가 넘어 가는 동시에 데이터 베이스 에 라이더호출 내역 이 완려 되었다는걸  저장한다 -->
			 </center>
		</table>	

</body>

</html>