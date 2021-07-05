<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html5>
<head>
<script language="javascript">
  function showPopup() { window.open("TimePopup_1.jsp", "a", "width=500, height=300,  "); }
  </script>
  
  
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
    
 
  
</head>
<body>
<center>
<h2> 시간을 선택하시오 </h2>
<input type="button" class="button" value="30분">
<input type="button" class="button" value="40분">
<input type="button" class="button" value="50분"><br>
<input type="button" class="button" value="60분">
<input type="button" class="button" value="70분">
<input type="button" class="button" value="80분">
<hr>
<input type=button  onclick="showPopup();" style="height:50px; width:400px;" value="더보기"></td></tr>

</center>
</body>
</html5>
