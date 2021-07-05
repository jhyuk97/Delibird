<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<!DOCTYPE html>
<html>
<head>
 <title>음식 정보</title>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
</head>
<body>

   
   <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data"); %>
   
<jsp:useBean id="conDB" class="Admin.KoreanFoodDelDB" scope="request" />
<% ResultSet rs = (ResultSet)conDB.getRs(data);
   while(rs.next()){
%>
  <br>
  <form class="food" method="post" action="/Delibird/KoreanFoodServlet">
  <input type="hidden" name="num" value="<%=data %>">
   <table style="margin:auto; width:70%">
   
    
      
      
     <tr>
      <td>&nbsp;</td>
      <td align="center">No.: </td>
      <td><%=rs.getString("홍보번호") %></td>
      <td>&nbsp;</td>
      
      
   <tr>
      <td>&nbsp;</td>
      <td align="center">이미지</td>
      <td><img src = "<%=rs.getString("웹IMG") %>" width=100, height=100></td>
      <td>&nbsp;</td>
      
      
     <tr>
      <td>&nbsp;</td>
      <td align="center">사진이름: </td>
      <td><%=rs.getString("사진이름") %></td>
      <td>&nbsp;</td>
      
     </tr>

     <tr>
      <td>&nbsp;</td>
      <td align="center">사진설명</td>
      <td><textarea name="memo" cols="50" rows="13"><%=rs.getString("사진설명") %></textarea></td>
    
      <%
      
   }
      conDB.closeDB();%>
      
      <td>&nbsp;</td>
     </tr>
	<tr>
      <td colspan="7"><input type="submit" style="margin:auto; float:right" value="삭제">
     
     </tr>
    </table>
</form>
</body>
</html>