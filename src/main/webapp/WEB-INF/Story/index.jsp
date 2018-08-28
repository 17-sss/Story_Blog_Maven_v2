<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Welcome to Story Blog!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	
	
	<style type="text/css">
		/* 배경 및 폰트 설정 */
		body, html {height: 100%}
		#banner-index{
			background-image: url("${pageContext.request.contextPath}/utilize/images/Wallpapers/camera.jpg");
		}
		#font-poiretone {
			font-family: 'Poiret One', cursive;
		}
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
		
		/* ### Modal ### */
		
		/* The Modal (background) */
		.modal {
		    display: none; /* Hidden by default */
		    position: fixed; /* Stay in place */
		    z-index: 1; /* Sit on top */
		    left: 0;
		    top: 0;
		    width: 100%; /* Full width */
		    height: 100%; /* Full height */
		    overflow: auto; /* Enable scroll if needed */
		    background-color: rgb(0,0,0); /* Fallback color */
		    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
		    padding-top: 60px;
		}
		
		/* Modal Content/Box */
		.modal-content {
		    background-color: #fefefe;
		    margin: 10% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
		    border: 1px solid #888;
		    width: 43%; /* Could be more or less, depending on screen size */
		}
		
		/* The Close Button (x) */
		.close {
		    position: absolute;
		    right: 25px;
		    top: 0;
		    color: #000;
		    font-size: 35px;
		    font-weight: bold;
		}
		.close:hover,
		.close:focus {
		    color: red;
		    cursor: pointer;
		}
		
		
		/* Add Zoom Animation */
		.animate {
		    -webkit-animation: animatezoom 0.6s;
		    animation: animatezoom 0.6s
		}
		
		/* Change styles for span and cancel button on extra small screens */	
		@keyframes animatezoom {
		    from {transform: scale(0)} 
		    to {transform: scale(1)}
		}
		
		@-webkit-keyframes animatezoom {
		    from {-webkit-transform: scale(0)} 
		    to {-webkit-transform: scale(1)}
		}
		#label-text{
			color: black;
		}
	</style>
	
	<style type="text/css">
		/* ==== Modal 관련 ==== */
		input[type=text], input[type=password] {
		    width: 100%;
		    padding: 12px 20px;
		    margin: 8px 0;
		    display: inline-block;
		    border: 1px solid #ccc;
		    box-sizing: border-box;
		}
		/* 로그인 버튼 */
		#btn {
		    background-color: #4CAF50;
		    color: white;
		    padding: 14px 20px;
		    margin: 8px 0;
		    border: none;
		    cursor: pointer;
		    width: 100%;
		}
		
		/* 로그인(?) 구역 */
		.login-container {
		    text-align: center;
		    margin: 0px -10px 12px 0px;
		    position: relative;
		}
		
		/* 로그인 모달 안 컨테이너 (Cancel버튼) 구역 설정 */
		.container-m {
		    padding: 16px;
		}
	</style>
	
	<script type="text/javascript">
		// 로그인 아이디, 비번 체크 알림
		function checkValue() {
	        var inputForm = eval("document.loginInfo");
	        
	        if(!inputForm.email.value) {
	            alert("이메일을 입력하세요");    
	            inputForm.email.focus();
	            return false;
	        }
	        
	        if(!inputForm.pwd.value) {
	            alert("비밀번호를 입력하세요");    
	            inputForm.pwd.focus();
	            return false;
	        }
	    }
	</script>
</head>
<body>
<!-- 전체 -->
<div class="story-height-i-100 story-overflow-hidden1" id="banner-index">

	<!-- 기본 디자인 (중앙) -->
	<div class="w3-center w3-text-white w3-animate-opacity w3-display-middle">
		<h1 class="w3-animate-top" style="font-size: 90px !important">
			<a id="font-poiretone" href="index" style="text-decoration: none;">Story Blog</a>
		</h1>
		<br><br>
		<i class="fas fa-angle-left w3-xlarge"></i>
		<input class="w3-button w3-large w3-hover-none" type="button" value="Login" style="margin-top: -10px;"
			onclick = "document.getElementById('login').style.display='block'">
		<i class="fas fa-angle-right w3-xlarge"></i>
		
		<p class="w3-center w3-small"><a href="${pageContext.request.contextPath}/user/accountForm">Sign Up</a></p>
	</div>
	<!-- end. 기본 디자인 (중앙) -->
	
	<!-- 로그인 modal --> 
	<div id="login" class="modal">
			     
	    <!-- 로그인 폼 -->
	    <form class="modal-content animate" name="loginInfo" method="post" 
	    action="${pageContext.request.contextPath}/user/LoginPro" onsubmit="return checkValue()">
	     
		    <!-- 로그인 모달 닫기 버튼-->
			<div class="login-container">
			    <span onclick="document.getElementById('login').style.display='none'" class="close" title="Close Modal">&times;</span>
		    </div>
		    	
	    	<!-- 로그인 입력폼 -->
	    	<div class="w3-container font-montserrat-c" style="padding-top: 3%;">
	    		<label id="label-text"><b>E-mail</b></label>
			    <input type="text" placeholder="이메일을 입력하세요" name="email">
			    <label id="label-text"><b>Password</b></label>
			    <input type="password" placeholder="비밀번호를 입력하세요" name="pwd">
			    	
		    	<div class="w3-center">
		    		 <input id="btn" type="submit" value="Login"/>
			    </div>
			    
			    <div class="container-m">
	    			<span class="w3-right">
	    				<a href="${pageContext.request.contextPath}/user/accountForm" class="w3-text-black">Sign Up</a>
	    			</span>
	    			<span>Forgot <a href="${pageContext.request.contextPath}/user/findpwd">password?</a></span>
	    		</div>
	    		
		    </div>
    	    <!-- end. 로그인 입력폼 -->
    	    
		</form>
		<!-- end. 로그인 폼 -->
		
	</div>
   <!--end. 로그인 modal -->
   
   
</div>

</body>
</html>