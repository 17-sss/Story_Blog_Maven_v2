<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>FAQ - Story Blog</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding" rel="stylesheet">
	<style type="text/css">
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
		
		.font-nanum-coding-c {
			font-family: 'Nanum Gothic Coding', monospace;
		}
	</style>
</head>	
<%@include file="/utilize/common/header.jsp"%>
<body>
<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">

	<div class="w3-container">
		
		<div style="margin: 2% 14.5% 2% 15%;">
			<div class="w3-container w3-white w3-round font-nanum-coding-c">
				<div class="w3-container w3-padding-16">
				
					<div style="margin-left: 24%;">
						<div class="w3-container w3-padding-16">
							<span>
								<b>1.</b> 
								일기장 (Board)은
								<span class="w3-text-red">"</span>
								<b class="font-montserrat-c">Reporting Date</b>
								<span class="w3-text-red">"</span>
								기준으로 정렬 됩니다.
							</span>
							<br>
							<span>
								<b>2.</b> 
								사진첩 (Gallery)은
								<span class="w3-text-red">"</span>
								<b class="font-montserrat-c">Date</b>
								<span class="w3-text-red">"</span>
								기준으로 정렬 됩니다.
							</span>
							<br>
							<span>
								<b>3.</b> 
								메인 (Home)의 사진은
								<span class="w3-text-red">"</span>
								<b class="font-montserrat-c">Date</b>
								<span class="w3-text-red">"</span>
								기준으로 정렬 됩니다.
							</span>
						</div>
					</div>	
					
					<div class="w3-center w3-margin-top">
						<input type="button" class="w3-light-gray w3-small w3-button" value="뒤로" onclick="history.go(-1)">
						<input type="button" class="w3-light-gray w3-small w3-button" value="글쓰기" onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_write'">
					</div>
				</div>	
			</div>
		</div>
		
	</div>
	
	<!-- footer  -->
	<div class="story-footer" style="background: #EAEAEA;">
		<div class="story-copyright">
			&copy; Powered by <a href="${pageContext.request.contextPath}/user/LogoutPro">Story Blog</a>
		</div>
	</div>
	<!-- end. footer -->
	
	
</div>			
</body>
</html>