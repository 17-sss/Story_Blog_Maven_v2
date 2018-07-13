<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>ConfirmEmail - Story Blog</title>
</head>
<body>
<c:if test="${result}">
	<div class="w3-container w3-center story-vertical-middle">
		<h4 style="margin-top: 125px;">이미 사용중인 이메일입니다.</h4>
	</div>
</c:if>	
<c:if test="${!result}">
	<div class="w3-container w3-center story-vertical-middle">
		<h4 style="margin-top: 125px;">입력하신 <b>${email}</b>은 사용할수 있는 이메일 입니다.</h4>
	</div>
</c:if>
</body>
</html>