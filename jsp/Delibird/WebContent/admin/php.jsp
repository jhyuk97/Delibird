<?xml version="1.0" encoding="EUC-KR" ?>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR" />
<title>Insert title here</title>
</head>
<body>
<?php
header("Content-Type: text/html; charset=UTF-8");
?>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
            if(data.userSelectedType=="R"){
                // userSelectedType : �˻� ������� ����ڰ� ������ �ּ��� Ÿ��

                // return type : R - roadAddress, J : jibunAddress

                // TestApp �� �ȵ���̵忡�� ����� �̸�
                window.TestApp.setAddress(data.zonecode, data.roadAddress, data.buildingName);
            }
            else{
                window.TestApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName);
            }       
        }
    }).open();
</script>
</body>
</html>