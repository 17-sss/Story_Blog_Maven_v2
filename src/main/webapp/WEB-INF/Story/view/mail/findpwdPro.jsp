<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>Check Email - Story Blog</title>
	
	<!-- result >> true (���� �̸��� ����, ���Ϻ����� ����) -->
	<c:if test="${result}">
	<script>
		location.href="${pageContext.request.contextPath}/mail/findpwd_mail?email=${email}";
	</script>
	</c:if>
	
	<!-- !result >> false  (������ �̸��� ������ ���Ϻ����� ����Ǹ� �ȵ�) -->
	<c:if test="${!result}">
	<script>
		alert("���Ե� �̸����� �����ϴ�.");
		location.href="${pageContext.request.contextPath}/mail/findpwd";
	</script>
	</c:if>
</head>
<body>
</body>
</html>