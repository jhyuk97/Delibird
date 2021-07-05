<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="Admin.*"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
	 table, th, td {
        border: 1px solid #bcbcbc;
      }
      table {
        width: 600px;
        
        float: left;
      }
    #topMenu {
            height: 30px;  /* 메인 메뉴의 높이 */
            width: 850px;  /* 메인 메뉴의 넓이 */
    }
    #topMenu ul {           /* 메인 메뉴 안의 ul을 설정함: 상위메뉴의 ul+하위 메뉴의 ul */
        list-style-type: none;  /* 메인 메뉴 안의 ul 내부의 목록 표시를 없애줌 */
        margin: 0px;            /* 메인 메뉴 안의 ul의 margin을 없앰 */
        padding: 0px;           /* 메인 메뉴 안의 ul의 padding을 없앰 */
    }
    #topMenu ul li {            /* 메인 메뉴 안에 ul 태그 안에 있는 li 태그의 스타일 적용(상위/하위메뉴 모두) */
        color: white;               /* 글씨 색을 흰색으로 설정 */
        background-color: #2d2d2d;  /* 배경 색을 RGB(2D2D2D)로 설정 */
        float: left;                /* 왼쪽으로 나열되도록 설정 */
        line-height: 30px;          /* 텍스트 한 줄의 높이를 30px로 설정 */
        vertical-align: middle;     /* 세로 정렬을 가운데로 설정 */
        text-align: center;         /* 텍스트를 가운데로 정렬 */
        position: relative;         /* 해당 li 태그 내부의 top/left 포지션 초기화 */
    }
    .menuLink, .submenuLink {           /* 상위 메뉴와 하위 메뉴의 a 태그에 공통으로 설정할 스타일 */
        text-decoration:none;               /* a 태그의 꾸밈 효과 제거 */
        display: block;                     /* a 태그의 클릭 범위를 넓힘 */
        width: 150px;                       /* 기본 넓이를 150px로 설정 */
        font-size: 12px;                    /* 폰트 사이즈를 12px로 설정 */
        font-weight: bold;                  /* 폰트를 굵게 설정 */
        font-family: "Trebuchet MS", Dotum; /* 기본 폰트를 영어/한글 순서대로 설정 */
    }
    .menuLink {     /* 상위 메뉴의 글씨색을 흰색으로 설정 */
        color: white;
    }
    .topMenuLi:hover .menuLink {    /* 상위 메뉴의 li에 마우스오버 되었을 때 스타일 설정 */
        color: red;                 /* 글씨 색 빨간색 */
        background-color: #4d4d4d;  /* 배경색을 밝은 회색으로 설정 */
    }
    .submenuLink {          /* 하위 메뉴의 a 태그 스타일 설정 */
        color: #2d2d2d;             /* 글씨 색을 RGB(2D2D2D)로 설정 */
        background-color: white;    /* 배경색을 흰색으로 설정 */
        border: solid 1px black;    /* 테두리를 설정 */
        margin-top: -1px;           /* 위 칸의 하단 테두리와 아래칸의 상단 테두리가 겹쳐지도록 설덩 */
    }
    .longLink {     /* 좀 더 긴 메뉴 스타일 설정 */
        width: 190px;   /* 넓이는 190px로 설정 */
    }
    .submenu {              /* 하위 메뉴 스타일 설정 */
        position: absolute;     /* html의 flow에 영향을 미치지 않게 absolute 설정 */
        height: 0px;            /* 초기 높이는 0px로 설정 */
        overflow: hidden;       /* 실 내용이 높이보다 커지면 해당 내용 감춤 */
        transition: height .2s; /* height를 변화 시켰을 때 0.2초간 변화 되도록 설정(기본) */
        -webkit-transition: height .2s; /* height를 변화 시켰을 때 0.2초간 변화 되도록 설정(구버전 크롬/사파라ㅣ) */
        -moz-transition: height .2s; /* height를 변화 시켰을 때 0.2초간 변화 되도록 설정(구버전 파폭) */
        -o-transition: height .2s; /* height를 변화 시켰을 때 0.2초간 변화 되도록 설정(구버전 오페라) */
    }
    .topMenuLi:hover .submenu { /* 상위 메뉴에 마우스 모버한 경우 그 안의 하위 메뉴 스타일 설정 */
        height: 130px;           /* 높이를 93px로 설정 */
    }
    .submenuLink:hover {        /* 하위 메뉴의 a 태그의 마우스 오버 스타일 설정 */
        color: red;                 /* 글씨색을 빨간색으로 설정 */
        background-color: #dddddd;  /* 배경을 RGB(DDDDDD)로 설정 */
    }
    </style>
</head>

<body>

<div id="topMenu" align="center">
         
      <ul>
            <li class="topMenuLi">
                <a class="menuLink" href="ReceptionCheck.jsp">매장관리</a>
                <ul class="submenu">
                    <li><a href="MarketApply.jsp" class="submenuLink">매장 신청</a></li>
                    <!-- 관리자가 점주의 매장 신청을 보는 곳 -->
                 
                    
                      <li><a href="market.jsp" class="submenuLink">매장 리스트</a></li>
                    
            
             </ul>
            </li>
         <li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="rider.jsp">라이더 관리</a>
                <ul class="submenu">
                    <li><a href="RiderPay.jsp" class="submenuLink">라이더 급여</a></li>
                    
                    <li><a href="RiderList.jsp" class="submenuLink">라이더 목록</a></li>
                    
                    <li><a href="Rental.jsp" class="submenuLink">대여 신청</a></li>
                    
                    <li><a href="RentalL.jsp" class="submenuLink">대여 내역</a></li>
                  
                   </ul>
            </li>
           <!--  --> 
               <li>|</li>
              <li class="topMenuLi">
                <a class="menuLink" href="Noticelist.jsp">공지사항</a>
            </li>
  
         <li>|</li>

            <li class="topMenuLi">
                <a class="menuLink" href="Realreport.jsp">1:1 문의</a>                
                  
            </li>
            
         <li>|</li>
         
            <li class="topMenuLi">
                <a class="menuLink" href="KoreanFood.jsp">한식 게시판</a>
 
            </li>
            </ul>
   </div>
   <br><br>
<form class="dateClass" method="POST" action="/Delibird/RPDateSer">
<select name="year">
<%
    Calendar cal = Calendar.getInstance();
 
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    
    for(int i = year - 5; i < year + 5; i++) {
            out.println("<option>" + i + "</option>");
    }
    
%>
</select>년

<select name="month">
<option>01</option>
<option>02</option>
<option>03</option>
<option>04</option>
<option>05</option>
<option>06</option>
<option>07</option>
<option>08</option>
<option>09</option>
<option>10</option>
<option>11</option>
<option>12</option>
</select>월
<input type="submit" name="go" value="조회">
</form>
<br>
<table class="riderpay"><!-- 테이블내용은 검색버튼 누르면서 띄울꺼임 처음에는 아무것도안뜸 -->
<colgroup>
	<col class = "month">
	<col class = "user">
	<col class = "count">
	<col class = "pay">
	<col class= "go">
</colgroup>

<thead>
	<tr>
		<th scope ="col" class = "number">년-달</th>
		<th scope ="col" class = "user">라이더아이디</th>
		<th scope ="col" class = "date">횟수</th>
		<th scope ="col" class = "pay">금액</th>
		<th scope ="col" class = "pay">지급</th>
	</tr>
</thead>
<tbody>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id ="conDB" class="Admin.RiderPayDB" scope="request"/>
	<%
	//out.print("<script>alert('확인');</script>");
	ResultSet rs;
		if(request.getAttribute("Syear") == null){
			rs = (ResultSet)conDB.getRs();
		}else{
			rs = (ResultSet)conDB.getRs(Integer.parseInt(request.getAttribute("Syear").toString()),Integer.parseInt(request.getAttribute("Smonth").toString()));
		}

		while(rs.next()){
			out.print("<tr>" + "<td>" + rs.getString(1)+ "</td> <td>" + rs.getString(2) + "</td> <td>" + rs.getString(3) + "</td> <td>" + rs.getString(4) + "</td> <td>");
			out.print("<input type='button' class='payBtn' value='지급하기'></td> </tr>");
			
		}
		conDB.closeDB();
		if(request.getAttribute("test") == "true"){
		out.print("<script>alert('확인');</script>");
		}
		%>
</tbody>
</table>
<script>
$(function(){
	$('.payBtn').click(function(){
		var Btn = $(this);
		
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var date = td.eq(0).text();
		var ID = td.eq(1).text();
		var money = td.eq(3).text();
		var realdata = {
				jsonData1 : JSON.stringify(date),
				jsonData2 : JSON.stringify(ID),
				jsonData3 : JSON.stringify(money)
		}
		
		$.post("/Delibird/RiderPay", realdata, function(result){
			return result;
		})
		
		//delclass.action="/Delibird/MarketApply_Servlet";
		//delclass.method = "post";
		//delclass.submit();
	})
});
</script>
</body>
</html>