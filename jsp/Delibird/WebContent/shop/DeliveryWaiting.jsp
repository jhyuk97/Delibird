<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">
<meta charset="EUC-KR">
<title>�ֹ� ���� �Ϸ� ����</title>


<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							<!-- Header -->
								<header id="header">
									<a href="AchopkeeperMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
														<li><a href="ReceptionCheck.jsp">�ֹ�����</a></li>
  												
  											  <li><a href="DeliveryWaiting.jsp">�ֹ�����</a></li>
									</ul>
								</header>



<%
	 request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="DB" class="Shop.DeliveryWaiting" scope="request"/>
<% 		
ResultSet rs = (ResultSet)DB.getRs(session.getAttribute("ID").toString()); %>
<div align = "center">

<table>
	<tr>
		<td>�ֹ���ȣ</td>
		<td>�ּ�</td>
		<td>�޴�</td>
		<td>�ݾ�</td>
		<td>�����Ȳ</td>
		<td>�ֹ� �ð�</td>
	</tr>
	<%
	while(rs.next()){
		%>
	<tr>
		<td><%=rs.getString("�ֹ���ȣ") %></td>
		<td><%=rs.getString("�����") %></td>
		<td><%=rs.getString("�޴�") %></td>
		<td><%=rs.getString("�ݾ�") %></td>
		<td><%=rs.getString("�����Ȳ") %></td>
		<td><%=rs.getString("ȣ��ð�") %></td>
	</tr>
<%} %>
</table>
</div>
</body>
</html>