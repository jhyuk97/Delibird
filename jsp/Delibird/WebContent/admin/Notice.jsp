<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<!DOCTYPE html>
<html>
<head>
  <title>공지사항 상세보기</title>

<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
</head>
<body>
		<div class="inner" style = "padding: 0px 100px 0px 100px;">
   <%
   request.setCharacterEncoding("UTF-8");
   String data = request.getParameter("data"); %>
<jsp:useBean id="conDB" class="Admin.NoticeDB" scope="request" />
<% ResultSet rs = (ResultSet)conDB.getRs(data);
   while(rs.next()){
%>
  <br>
  <form class="NoticeDel" method="post" action="/Delibird/NoticeServlet">
  <input type="hidden" name="num" value="<%=data %>">
   <table>
     <tr>
      <td>&nbsp;</td>
      <td align="center">제목</td>
      <td><%=rs.getString("제목") %></td>
      <td>&nbsp;</td>
     </tr>

     <tr>
      <td>&nbsp;</td>
      <td align="center">내용</td>
      <td><textarea name="memo" cols="50" rows="13"><%=rs.getString("내용") %></textarea></td>
      <%}
      conDB.closeDB();%>
      <td>&nbsp;</td>
     </tr>

     <tr align="center">
      <td>&nbsp;</td>
      <td colspan="2"><input type="submit" value="삭제">
      <td>&nbsp;</td>
     </tr>
    </table>
</form>
</div>
</body>
</html>