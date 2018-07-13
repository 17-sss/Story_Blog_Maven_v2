<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>File Type Check <%= "&" %> Update</title>
	<c:if test="${filechk1==1 || filechk2==1 || filechk3==1 || filechk4==1 || filechk5==1 || filechk6==1}">
		<script>
			alert("���� ���ε�� jpg,png,jpeg,gif�� �����մϴ�.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${(filechk1==0 && filechk2==0 && filechk3==0 && filechk4==0 && filechk5==0 && filechk6==0) && chk == 1}">
		<!-- ��ü  -->
		<c:if test="${chk_d_diary == null && opt == null && search == null}">
		<script>
			alert("���� �Ǿ����ϴ�.");
		</script>
		<meta http-equiv="Refresh" content="0; url=${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&pageNum=${pageNum}">
		</c:if>
		
		<c:if test="${chk_d_diary == null && opt != null && search != null}">
		<script>
			alert("���� �Ǿ����ϴ�.");
			document.location.href=
				'${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${pageNum}';
		</script>
		</c:if>
		<!-- end. ��ü -->
		<!-- ���� -->
		<c:if test="${chk_d_diary != null && opt == null && search == null}">
		<script>
			alert("���� �Ǿ����ϴ�.");
			document.location.href=
				'${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&pageNum=${pageNum}';
		</script>
		</c:if>
		
		<c:if test="${chk_d_diary != null && opt != null && search != null}">
		<script>
			alert("���� �Ǿ����ϴ�.");
			document.location.href=
				'${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&d_diary=' 
						+ encodeURI('${d_diary}') + '&search=' + encodeURI('${search}') +'&opt=${opt}&pageNum=${pageNum}';
		</script>
		</c:if>
		<!-- end. ���� -->
	</c:if>
	
	<c:if test="${(filechk1==0 && filechk2==0 && filechk3==0 && filechk4==0 && filechk5==0 && filechk6==0) && chk != 1}">
		<script>
			alert("���� �� �� �����ϴ�.");
			location.go(-1);
		</script>
	</c:if>
	
	
</head>
<body>

</body>
</html>