<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${s_name}'s Main - Story Blog</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	
	<style type="text/css">
		#banner-1{
			background-image: url("${pageContext.request.contextPath}/utilize/images/Wallpapers/camera.jpg");
			background-position: center center;
			opacity: 0.9;
			
		}
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
	</style>
</head>
<%@include file="/utilize/common/header.jsp" %>
<body>

<div style="margin-top: 39px; background: #f1f1f1; width: 100%;" class="font-montserrat-c">
	
	<!-- main - button(write) & section -->
	<div class="w3-container story-height-i-60 story-overflow-hidden2" id="banner-1">
		<div class="story-div-table-60">
			<span class="w3-text-white story-vertical-middle">
				<span style="font-size: 45px;">How was your day?</span><br>
				<input type="button" class="w3-button w3-blue w3-margin-top" value="Write Your Story!"
				onclick="location.href='${pageContext.request.contextPath}/diary/diary_write'">
			</span>
		</div>
	</div>
	<!-- end. main - button(write) & section -->
	
	
	<!-- main - display image -->
	<div class="w3-container">
		
		<div class="w3-center w3-margin">
			<span style="font-size: 30px;">What's New?</span>
		</div>

		<div style="margin: 0% 7%;">
			
			<!-- Ver.1 -->
			<%-- <div class="story-third w3-margin-bottom" style="margin: 0% 0.15%;">
				<div class="w3-padding-16 w3-center w3-round" style="margin: 0% 3.5%; background: #f6f6f6;">
					<img src="${pageContext.request.contextPath}/utilize/images/Wallpapers/long2.jpg" alt="alt" class="story-autoimg">
				</div>
			</div> --%>
			
			<!-- Ver.2 -->
			<%-- <div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/utilize/images/Wallpapers/04.jpg">
					</div>
				</div>
			</div> --%>
			
			<!-- Ver.2 ++ -->
			<%-- <div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/utilize/images/Wallpapers/long2.jpg" class="story-autoimg2">
					</div>
				</div>
			</div> --%>
			
			<c:forEach items="${recent_picture}" var="diary">
			
			<c:if test="${diary.filename1 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename1}" alt="${diary.filename1}" class="story-autoimg2">
					</div>
				</div>
			</div>
			</c:if>	
			
			<c:if test="${diary.filename2 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename2}" alt="${diary.filename2}" class="story-autoimg2">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename3 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename3}" alt="${diary.filename3}" class="story-autoimg2">
					</div>
				</div>
			</div>	
			</c:if>
			
			<c:if test="${diary.filename4 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename4}" alt="${diary.filename4}" class="story-autoimg2">
					</div>
				</div>
			</div>	
			</c:if>
			
			<c:if test="${diary.filename5 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename5}" alt="${diary.filename5}" class="story-autoimg2">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename6 != null}">
			<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
				<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename6}" alt="${diary.filename6}" class="story-autoimg2">
					</div>
				</div>
			</div>
			</c:if>
			
			</c:forEach>
			
				
		</div>
	</div>
	<!-- end. main - recent picture -->
	
	<!-- footer -->
	<div class="story-footer" style="background: #EAEAEA;">
		<div class="story-copyright">
			&copy; Powered by <a href="${pageContext.request.contextPath}/user/LogoutPro">Story Blog</a>
		</div>
	</div>
	<!-- end. footer -->
	
</div>
</body>
</html>