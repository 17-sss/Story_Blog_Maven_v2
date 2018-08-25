<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta http-equiv="Refresh" content="0; url=index">
	
	<c:if test="${check==1}">
		<c:if test="${search == null}">
		<script>
			alert("삭제되었습니다.");
			location.href="${pageContext.request.contextPath}/admin/admin_page?pageNum=${pageNum}";
		</script>
		</c:if>
		<c:if test="${search != null}">
		<script>
			alert("삭제되었습니다.");
			location.href="${pageContext.request.contextPath}/admin/admin_page?search=" + encodeURI('${search}') + 
					"&opt=${opt}&pageNum=${pageNum}";
		</script>
		</c:if>
	</c:if>	

	<c:if test="${check!=1 && check_diary!=1}">
	<script type="text/javascript">
		alert("삭제 불가");
		history.go(-1);
	</script>
	</c:if>
</head>
<body>
	
</body>
</html>