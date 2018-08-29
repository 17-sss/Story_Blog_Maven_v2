<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>Check Email - Story Blog</title>
	
	<!-- result >> true (가입 이메일 있음, 메일보내기 실행) -->
	<c:if test="${result}">
	<script>
		location.href="${pageContext.request.contextPath}/mail/findpwd_mail?email=${email}";
	</script>
	</c:if>
	
	<!-- !result >> false  (가입한 이메일 없으면 메일보내기 실행되면 안됨) -->
	<c:if test="${!result}">
	<script>
		alert("가입된 이메일이 없습니다.");
		location.href="${pageContext.request.contextPath}/mail/findpwd";
	</script>
	</c:if>
</head>
<body>
</body>
</html>