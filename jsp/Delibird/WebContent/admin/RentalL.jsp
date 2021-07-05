<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.sql.*"  import="Admin.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대여 내역</title>
<link rel="stylesheet" href="Design/resource/assets/css/main.css">
<link rel="stylesheet" href="Design/resource/assets/css/fontawesome-all.min.css">

<!-- Header -->
							<div class="inner" style = "padding: 0px 100px 0px 100px;" >
								<header id="header">
									<a href="AdminMain.jsp" class="logo"><strong>Delibird</strong> by CS16</a>
									<ul class="icons">
							
												<li><a href="./RiderList.jsp">라이더 목록</a></li>
												<li><a href="./Rental.jsp">대여 신청</a></li>
												<li><a href="./RentalL.jsp">대여 내역</a></li>			
									</ul>
													
					
							
										</header>
								</div>
								</head>
					
								

</head>



<div class="inner" style = "padding: 0px 100px 0px 100px;" >

<script>
 
    function searchCheck(frm){
        //검색
       
        if(frm.keyWord.value ==""){
            alert("검색 단어를 입력하세요.");
            frm.keyWord.focus();
            return;
        }
        frm.submit();      
    }
</script>





<body>
		
<%
	 request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="conDB1" class="Admin.RentalConnect" scope="request" />


	<% 	
		ResultSet rs1;
		String name = request.getParameter("SearchName");
		if(name == null || name == ""){
			rs1 = (ResultSet)conDB1.getRs();
		}
		else{
			rs1 = (ResultSet)conDB1.getConnect(name);
		}
	%>


		  <form id="" name="Search" method="Get" action="RentalL.jsp">

			
			<input type="text" name="SearchName" style="width:94%; float:left" placeholder="라이더 아이디를 입력해주세요."/>
            <input type=submit value="검색" style="float:right" />
					
		</form>
		<br>


<br>
<table class = "RentalL">
<tr>
<td> 라이더 ID </td>
<td> 번호판 </td>
<td>대여 어부</td>
<td>이름 </td>
<td>대여일 </td>
</tr>


<%
	 while(rs1.next()){
%>





<tr>

<td><%=rs1.getString("라이더ID") %></td>
<td><%=rs1.getString("번호판") %></td>
<td><%=rs1.getString("대여여부") %></td>
<td><%=rs1.getString("이름") %></td>
<td><%=rs1.getString("대여일") %></td>
	<tr>

</tr>

<%

}
%>


<%
conDB1.closeDB();
%>

</table>

</body>
</div>
</html>