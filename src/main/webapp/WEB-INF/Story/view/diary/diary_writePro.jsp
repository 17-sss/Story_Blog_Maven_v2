<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>File Type Check <%= "&" %> Write</title>
	<c:if test="${filechk1==1 || filechk2==1 || filechk3==1 || filechk4==1 || filechk5==1 || filechk6==1}">
		<script>
			alert("파일 업로드는 jpg,png,jpeg,gif만 가능합니다.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${filechk1==0 && filechk2==0 && filechk3==0 && filechk4==0 && filechk5==0 && filechk6==0}">
		<meta http-equiv="Refresh" content="0; url=${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&pageNum=${pageNum}">
	</c:if>
	
	
</head>
<body>

</body>
</html>