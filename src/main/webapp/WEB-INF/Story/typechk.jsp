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
			alert("프로필 사진 업로드는 jpg,png,jpeg,gif만 가능합니다.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${result}">
		<script>
			alert("이메일이 중복됩니다. 다른 이메일을 입력해주세요.")
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${filechk==0 && !result}">
		<script>
			alert("가입 되었습니다.");
			location.href="${pageContext.request.contextPath}/index";
		</script>
	</c:if>
</head>
<body>
</body>
</html>