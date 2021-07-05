<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<head>
    <meta charset="utf-8"/>
	<title>공지사항 작성</title>
    <link rel="stylesheet" href="Design/resource/assets/css/main.css">
	<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">

</head>
<body>

 			<div class="inner" style = "padding: 0px 300px 0px 300px; margin:auto;">
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


			
			
			<br>
			
    <form method="POST" action="/Delibird/WriteServlet">
    <table>
    
    <tr> 
    <input type="text" name="title" id="title" value="" placeholder="제목을 작성해주세요.">   
    </tr>
    <br>
    <tr>
    <input type="text" name="memo" id="memo" value="" placeholder="내용을 작성해주세요" style="height:40%">
    </tr>
    <br>
	<ul class="action">
	<div style="float:right">
	<input type="submit" value="등록">
	&nbsp;&nbsp;
	<input type="button" value="취소" onClick = "GoBack();">
	</div>
	</ul>
	</div>
	</table>
</form> 
			
<script>      
function GoBack(){
        	location.href="admin/Noticelist.jsp";
        }
</script>
</div>
</div>
</body>
</html>
