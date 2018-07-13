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
	
	<script type="text/javascript">
		function confirmEmail() {
			if (document.userInfo.email.value == "") {
				alert("이메일을 입력하세요.");
				return;
			}
			url = "confirmEmail?email=" + document.userInfo.email.value;
			open(url, "confirm", "toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizeble=no, width=400, height=350");
			document.userInfo.idDuplication.value="idCheck";
		}
		
		function inputIdChk() {
			document.userInfo.idDuplication.value = "idUncheck";
		}
		
		function checkValue() {
			var form = document.userInfo;
			if(!form.email.value){
	            alert("이메일을 입력하세요.");
	            return false;
	        }
	        if(!form.name.value){
	            alert("이름을 입력하세요.");
	            return false;
	        }
	        
	        if(!form.pwd.value){
	            alert("비밀번호를 입력하세요.");
	            return false;
	        }
	        
	        if(form.pwd.value != form.passchk.value){
	        	alert("비밀번호 확인란에 비밀번호를 동일하게 입력하세요.");
	            return false;
	        } 
	        
	        if(form.idDuplication.value != "idCheck"){
	            alert("아이디 중복체크를 해주세요.");
	            return false;
	        }
	    }
	</script>
</head>
<body>
<div id="banner-acc" class="story-height-i-100 story-overflow-hidden1">

	<div class="w3-animate-opacity w3-display-middle">
		<div class="w3-center w3-light-gray w3-container">
			<span id="font-poiretone" class="w3-jumbo">Story Blog</span>
		</div>
		
		<form method="post" name="userInfo" enctype="multipart/form-data" 
		action="${pageContext.request.contextPath}/user/accountPro" onsubmit="return checkValue()">
		
			<div class="w3-container w3-light-gray font-montserrat-c story-padding-t16-b24">
			
				<div style="margin: 0 10% 0;">	
					<div class="w3-margin-top">
						<i class="far fa-envelope" style="font-size: 24px;">&nbsp;</i>
						<input class="w3-button w3-small w3-light-blue w3-right" type="button" value="중복확인" onclick="confirmEmail(this.form)" style="font-size: 8pt;  display: inline-block;">
						<span style="font-size: 20px;">Email</span>
						<input type="email" class="w3-white story-input" name="email" onkeydown="inputIdChk()">
						<input type="hidden" name="idDuplication" value="idUncheck">
					</div>
					
					<div class="w3-margin-top">
						<i class="fas fa-key" style="font-size: 24px;">&nbsp;</i>
						<span style="font-size: 20px;">Password</span>
						<input type="password" class="w3-white story-input" name="pwd">
					</div>
					
					<div class="w3-margin-top">
						<i class="fas fa-key" style="font-size: 24px; color:#FF4848;">&nbsp;</i>
						<span style="font-size: 20px;">Password Check</span>
						<input type="password" class="w3-white story-input" name="passchk">
					</div>
					
					<div class="w3-margin-top">
						<i class="far fa-id-badge" style="font-size: 24px;">&nbsp;</i>
						<span style="font-size: 20px;">Name</span>
						<input type="text" class="w3-white story-input" name="name">
					</div>
					
					<div class="w3-margin-top">
						<i class="fas fa-phone" style="font-size: 24px;">&nbsp;</i>
						<span style="font-size: 20px;">Tel</span>
						<input type="number" class="w3-white story-input" name="tel">
					</div>
					
					<div class="w3-margin-top">
						<i class="fas fa-birthday-cake" style="font-size: 24px;">&nbsp;</i>
						<span style="font-size: 20px;">Birthday</span>
						<input type="date" class="w3-white story-input" name="birth">
					</div>
					
					<div class="w3-margin-top">
						<i class="far fa-image" style="font-size: 24px;">&nbsp;</i>
						<span style="font-size: 20px;">Photo</span>
						<input type="file" class="w3-white story-input" name="filename">
					</div>
					
			
					
					<div class="w3-container w3-right">
						<input type="submit" class="w3-button w3-round w3-gray w3-text-white w3-margin-top" value="Join">
						<input type="button" class="w3-button w3-round w3-gray w3-text-white w3-margin-top" value="Cancel"  OnClick="window.location='${pageContext.request.contextPath}/index'">
					</div>
				
				</div>
			</div>
		</form>
	</div>
	
</div>
</body>
</html>