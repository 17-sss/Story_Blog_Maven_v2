<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>File Type Error</title>
	<c:if test="${filechk==1}">
		<script>
			alert("������ ���� ���ε�� jpg,png,jpeg,gif�� �����մϴ�.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${result}">
		<script>
			alert("�̸����� �ߺ��˴ϴ�. �ٸ� �̸����� �Է����ּ���.")
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${filechk==0 && !result}">
		<script>
			alert("���� �Ǿ����ϴ�.");
			location.href="${pageContext.request.contextPath}/index";
		</script>
	</c:if>
</head>
<body>
</body>
</html>