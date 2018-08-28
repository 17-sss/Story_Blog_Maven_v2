<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Sign Up - Story Blog</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

	<style type="text/css">
		#banner-acc{
			background-image: url("${pageContext.request.contextPath}/utilize/images/Wallpapers/camera.jpg");
			opacity: 0.9;
		}
		#font-poiretone {
			font-family: 'Poiret One', cursive;
		}
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
	</style>
</head>
<body>
<div id="banner-acc" class="story-height-i-100 story-overflow-hidden1">
	
	<form method="post" name="findpwd" action="${pageContext.request.contextPath}/mail/findpwd_mail">
	
		<div class="w3-animate-opacity w3-display-middle" style="width: 40%;">
			<div class="w3-center w3-light-gray w3-container">
				<span id="font-poiretone" class="w3-jumbo">Story Blog</span>
			</div>
			<div class="w3-container w3-light-gray font-montserrat-c story-padding-t16-b24">
				<div style="margin: 0 10% 0;">
					<div class="w3-margin-top">
						<span>
							<i class="far fa-envelope" style="font-size: 24px;">&nbsp;</i>
							<span class="font-montserrat-c">Email</span>
						</span>
						<input type="text" name="to_email" class="w3-white story-input" placeholder="Email로 임시비밀번호가 전송됩니다.">
					</div>
					
					<div class="w3-margin-top w3-right">
						<input type="submit" class="w3-round w3-button w3-blue" value="Submit">
						<input type="button" class="w3-round w3-button w3-gray w3-text-white" value="Cancel" onclick="history.go(-1);">
					</div>
					
					
				</div>
			</div>
		</div>
	</form>	
</div>
</body>
</html>