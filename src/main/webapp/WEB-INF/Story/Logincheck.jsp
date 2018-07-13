<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Login Error</title>
	<c:if test="${check==0}">
		<script type="text/javascript">
			alert("비밀번호가 틀립니다.");
			history.back();
		</script>
	</c:if>
	
	<c:if test="${check==-1}">
		<script type="text/javascript">
			alert("이메일이 존재하지 않습니다.");
			history.back();
		</script>
	</c:if>
</head>
<body>
</body>
</html>