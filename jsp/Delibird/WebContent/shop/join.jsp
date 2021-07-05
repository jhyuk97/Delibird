<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Delibird/JoinGo" Method="post">
	아이디 : <input type= "text" name = "ID" size = "10"><br/>
	비밀번호 : <input type= "text" name = "PW" size = "10"><br/>
	이름 : <input type= "text" name = "이름" size = "10"><br/>
	전화번호 : <input type= "text" name = "전화번호" size = "10"><br/>
	매장번호 : <input type= "text" name = "매장번호" size = "20"><br/>
	사업자번호 : <input type= "text" name = "사업자번호" size = "20"><br/>
	
	<input type="submit" value="회원가입">
</form>

</body>
</html>