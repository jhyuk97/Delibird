<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <meta name="description" content="The Overflowing church website's main page" />
    <meta name="author" content="unikys@gmail.com" />
    <title>점주</title>
    <style>
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
        height: 93px;           /* 높이를 93px로 설정 */
    }
    .submenuLink:hover {        /* 하위 메뉴의 a 태그의 마우스 오버 스타일 설정 */
        color: red;                 /* 글씨색을 빨간색으로 설정 */
        background-color: #dddddd;  /* 배경을 RGB(DDDDDD)로 설정 */
    }
    </style>
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<center> <!-- 가운대 정렬  -->
 <h1>딜리버드</h1>
	<div id="topMenu" >
			
		<ul>
            <li class="topMenuLi">
                <a class="menuLink" href="ReceptionCheck.jsp">주문</a>
                <ul class="submenu">
                    <li><a href="ReceptionCheck.jsp" class="submenuLink">주문접수확인</a></li>
                    <!--  배달지 정보가 들어옴 (주문 접수)버튼 확인 으로 넘어가게끔  -->
                    <!-- 버튼 클릭 만들고     손님 에게 @@ 시간 보내기 클릭 화면으로 이동  -->
                    <!--  시간 클릭 하면 접수 완료 화면 으로 넘어감   -->
                    <!--  시간 클릭 하면 여기있던 정보가 접수 완료 로 넘어감   -->
                      <li><a href="DeliveryReception.jsp" class="submenuLink">접수완료</a></li>
                      <!-- 주문 접수 받은내용이 일로 넘어오고    -->
                      <!--  정보고 들어오고 라이더 가 배달 완료 버튼을 클릭 하면 이정보고 배달 완료 화면으로 넘어간다   -->
                      <li><a href="DeliveryCompleted.jsp" class="submenuLink">배달완료 </a></li>
                      <!-- 그냥 정보만 떠있음  -->
                <li><a href="" class="submenuLink"></a></li>
</ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="rider.jsp">라이더호출</a>
                <ul class="submenu">
                    <li><a href="riderdetail.jsp " class="submenuLink">라이더 호출 내역 </a></li>
                  
                   </ul>
            </li>
           <!--  --> 
         		<li>|</li>
           	<li class="topMenuLi">
                <a class="menuLink" href="Menumanagement.jsp">메뉴관리</a>
            </li>
  
			<li>|</li>

            <li class="topMenuLi">
                <a class="menuLink" href="Statistics.jsp">통계</a>
                  <ul class="submenu">
                    <li><a href="" class="submenuLink">매출통계</a></li>
                    <li><a href="" class="submenuLink">메뉴통계</a></li>
                   </ul>
            </li>
            
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="Storeedit.jsp">수정</a>
                <ul class="submenu">
                    <li><a href="Storeedit.jsp" class="submenuLink">매장수정</a></li>
                     <li><a href="Myedit.jsp" class="submenuLink">정보수정 </a></li>
                </ul>
            </li>
            
            
            
	</div>
<center>
	<h1 align="center" >정보수정   </h1>
        <table border=5 cellspacing="1" cellpadding="10">
	<tr>
		<td align="center" >ID</td> <!-- address 1 매장 주소 라는 뜻   -->
		<td><input type=text  name=iD ></td>
	</tr>
	
	<tr>
		<td align="center" >비밀번호</td><!-- address 2 고객 주소 라는 뜻   --> 
		<td><input type=text  name=pw></td>
		
	</tr>
	
	<tr>
		<td align="center">사업자번호</td>
		<td><input type=text size= name=number></td>
	</tr>
	
	<tr>
		<td align="center" >이름</td>
		<td><input type=text size=20 name=myname></td>
	</tr>
	<tr>
		<td align="center">전화번호</td>
		<td><input type=text size= name=colnumber></td>
	</tr>
	
	<tr>
		<td align="center">매장번호</td>
		<td><input type=text size= name=Shopnumber></td>
	</tr>
	
	
	
	
	<tr>
	<td colspan=2 align="center">
		<input type=button  style="height:25px; width:200px;" value="수정하기"></td></tr>
			<!--   데이터가 넘어 가는 동시에 데이터 베이스 에 라이더호출 내역 이 완려 되었다는걸  저장한다 -->
			 </center>
		</table>	

</body>
</html>