<!-- DiaryDBBean Delete User Pro -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<c:if test="${check==1}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta http-equiv="Refresh" content="0; url=user_main?pageNum=${pageNum}"> <!-- ���߿� �� ������ ���� �ڷ� ���� �����. ������ ������ main���ΰ� -->

	<script type="text/javascript">
		alert("�����Ǿ����ϴ�.");
	</script>

</c:if>

<c:if test="${check!=1}"><%-- <% } else { %> --%>
	<script type="text/javascript">
		alert("���� �Ұ�");
		history.go(-1);
	</script>
</c:if>	
</head>
<body>
	
</body>
</html>