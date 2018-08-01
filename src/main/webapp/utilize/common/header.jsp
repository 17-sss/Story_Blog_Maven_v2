<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<style type="text/css">
		#font-poiretone {
			font-family: 'Poiret One', cursive;
		}
		#font-montserrat {
			font-family: 'Montserrat', sans-serif;
		}
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
	</style>
</head>
<body>
<div class="w3-top">
	<div class="w3-bar w3-white" style="border-bottom: #f6f6f6 solid 0.1px;">
		<span style="font-size: 24px; margin: 1%;" id="font-poiretone">
			<span title="Story Blog">Story Blog</span>
		</span>
		
		<span>
			<a href="${pageContext.request.contextPath}/user/LogoutPro" style="font-size: 24px; text-decoration: none; margin: 0.5% 1% 0.5% 0;" class="w3-hover-text-gray w3-right">
				<span class="fas fa-sign-in-alt" title="Logout"></span>
			</a>
			<a href="${pageContext.request.contextPath}/user/user_page" style="font-size: 24px; text-decoration: none; margin: 0.5% 1% 0.5% 0;" class="w3-hover-text-gray w3-right">
				<span class="fas fa-user-cog" title="My Page"></span>
			</a>
		</span>	
	
		<div class="w3-dropdown-hover w3-hover-text-gray w3-right" style="margin: 0.4% 0.75% 0% 0%;">
       		<span style="font-size: 20pt;"> 
				<span class="fas fa-bars" title="Menu" ></span>
			</span>
	        <div class="w3-dropdown-content w3-center" style="right:2em; top:38px; font-size: 10pt; text-decoration: none; min-width: 8%; ">
	
	        	<hr style="margin-top: -1%;">
	        	<a href="${pageContext.request.contextPath}/diary/diary_board" class="w3-hover-text-gray">
					<span class="fas fa-sort-alpha-down" title="Board">&nbsp;
						<span class="font-montserrat-c">Board</span>
					</span>
				</a>
	        	<hr>
				<a href="${pageContext.request.contextPath}/diary/diary_gallery" class="w3-hover-text-gray">
					<span class="fas fa-images" title="Gallery">&nbsp;
						<span class="font-montserrat-c">Gallery</span>
					</span>
				</a>
				<hr style="margin-bottom: -0.5%;">

	        </div>
		</div>
		
		<span>
			<a href="${pageContext.request.contextPath}/user/user_main" style="font-size: 24px; text-decoration: none; margin: 0.5% 0.7% 0.5% 0%;" class="w3-hover-text-gray w3-right">
				<span class="fas fa-home" title="Home"></span>
			</a>
		</span>
		
		<!-- FAQ 필요 없어짐. 정렬기능 다 만듬 -->
		<%-- <span>
			<a href="${pageContext.request.contextPath}/user/user_faq" style="font-size: 12px; text-decoration: none; margin: 1.2% 1.2% 0.7% 0%;" class="w3-hover-text-light-gray w3-text-gray w3-right">
				<span class="far fa-question-circle" title="FAQ"></span>
			</a>
		</span> --%>
		
		

		
		<span class="w3-right" style="margin: 0.3% 0.5% 0% 0%;">
			<img src="${pageContext.request.contextPath}/userSave/${s_filename}" style="height:30px; width:30px; margin-bottom: 0.4%;">
			&nbsp;
			<span>
				<span style="font-size: 9pt;">
					Hi! ${s_name}.
				</span>		
			</span>
		</span>
		
	</div>
</div>
</body>
</html>