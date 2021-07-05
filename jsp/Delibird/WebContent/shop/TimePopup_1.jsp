<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>시간선택 창</title>
	<style>
	.button {

    width:100px;
    background-color: #ffcc33; 
    border: none;
    color:#fff;
    padding: 15px 0;
    text-align: center; 
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 4px;
    cursor: pointer;
    border-radius:10px 0 10px 0;
}
	table{
    	width : 100%;
    	border : 1px solid #4444;
    	}
    </style>
  <script language="javascript">
  function showPopup() { window.open("08_2_popup.html", "시간선택", "width=400, height=300, left=100, top=50"); }
  </script>
</head>
<body>
<center>
<h2> 시간을 선택하시오 </h2>
<input type="button" class="button" value="90분">
<input type="button" class="button" value="100분">
<input type="button" class="button" value="110분"><br>
<input type="button" class="button" value="120분">
<input type="button" class="button" value="130분">
<input type="button" class="button" value="140분">
</center>
</body>
</html>