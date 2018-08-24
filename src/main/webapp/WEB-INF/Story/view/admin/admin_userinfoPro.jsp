<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>File Type Check <%= "&" %> Update</title>
	<c:if test="${filechk==1}">
		<script>
			alert("프로필 사진 업로드는 jpg,png,jpeg,gif만 가능합니다.");
			location.href="${pageContext.request.contextPath}/admin/admin_userinfo?userN=${num}";
		</script>
	</c:if>
	<c:if test="${filechk==0 && chk==1}">
		<script>
			alert("변경되었습니다.");
			location.href="${pageContext.request.contextPath}/admin/admin_page";
		</script>
	</c:if>
	<c:if test="${filechk==0 && chk!=1}">
		<script>
			alert("변경 할 수 없습니다.");
			location.href="${pageContext.request.contextPath}/admin/admin_userinfo?userN=${num}";
		</script>
	</c:if>

</head>
<body>

</body>
</html>