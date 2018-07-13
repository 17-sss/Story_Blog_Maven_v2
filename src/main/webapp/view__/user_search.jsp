<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>'${search}' - Story Blog Search </title>
</head>
<body>
<!-- 전체 틀 div ★ --> 
<div>&nbsp;

<!-- 상단 바, 사이드 바 간격 -->
	<div style="margin-top:54px; margin-left: 10%;"><br>
		
		<!-- 양 옆 간격 -->
		<div style="margin-left: 10%; margin-right: 10%;">	
			
			<!-- 컨텐츠(?) -->
			
			<div class="w3-card w3-round w3-white w3-padding-16">
				<h4 class=w3-center>검색어: ${search}</h4>
				<table class="w3-table w3-bordered w3-margin-top" style="width: 80%; margin: 0% 10%;">
					<tr class="w3-center w3-gray" >
						<td align="center" class="w3-center w3-border" width="50">번호</td>
						<td align="center" class="w3-center w3-border" width="80">제목</td>
						<td align="center" class="w3-center w3-border" width="100">내용</td>
						<td align="center" class="w3-center w3-border" width="100">작성일</td>
					</tr>
					
					<c:forEach var="diary" items="${diarySearch}">
	          		<tr height="30">
	          			<td align="center" class="w3-center w3-border" width="50"><p>${number}</p></td>
	         		 	<c:set var="number" value="${number-1}" />
	        	    	<td align="center" class="w3-center w3-border" width="80">
	        	    		<p> <a href="${pageContext.request.contextPath}/story/user_content?num=${diary.num}&pageNum=${currentPage}"><b>${diary.subject}</b></a> </p>
	        	    	</td>
	        	    	<td align="center" class="w3-center w3-border" width="100">
	        	    		<c:choose>
								<c:when test="${fn:length(diary.content) > 10}">
									${fn:substring(diary.content, 0, 9)}...
								</c:when>
								<%-- <c:otherwise>
									${diary.content} 
								</c:otherwise> --%>
							</c:choose>
	        	    	</td>
	          			<td align="center" class="w3-center w3-border" width="100"><p>${diary.cdate}</p></td>
	          		</tr>
	          		</c:forEach>
				</table>
				
				<div class = "w3-center w3-white w3-margin">
	       		<c:if test="${count>0}"> 
					<c:if test="${startPage > bottomLine}">
						<a href="user_search?pageNum=${startPage - bottomLine}">[이전]</a>
					</c:if>
		
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="user_search?pageNum=${i}&search=${search}&email=${email}">
							<c:if test="${i != currentPage}">[${i}]</c:if>
								<c:if test="${i == currentPage}">
									<font color='red'>[${i}]</font>
								</c:if>
						</a>
					</c:forEach>
		
					<c:if test="${endPage < pageCount}">
						<a href="user_search?pageNum=${startPage + bottomLine}">[다음]</a>
					</c:if>
				</c:if>
				</div>
				
			</div>
				
			<!-- end. ? -->
			
		</div>
		<!-- end. 양 옆 간격 -->
		
	</div>
<!-- end. 상단 바, 사이드 바 간격 -->


</div>
<!-- end. 전체 틀 div ★ --> 

</body>
</html>