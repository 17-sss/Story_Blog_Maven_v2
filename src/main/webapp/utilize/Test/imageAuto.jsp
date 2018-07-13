<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<style type="text/css">
		.thumbnail-wrappper {
			width: 25%;
		}
		
		.thumbnail {
			position: relative;
			padding-top: 100%; /* 1:1 ratio */
			overflow: hidden;
		}
		
		.thumbnail .centered {
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			-webkit-transform: translate(50%, 50%);
			-ms-transform: translate(50%, 50%);
			transform: translate(50%, 50%);
		}
		
		.thumbnail .centered img {
			position: absolute;
			top: 0;
			left: 0;
			max-width: 100%;
			height: auto;
			-webkit-transform: translate(-50%, -50%);
			-ms-transform: translate(-50%, -50%);
			transform: translate(-50%, -50%);
		}
	</style>
</head>
<body>

<div class="w3-container">
	<div class="thumbnail-wrapper">
		<div class="thumbnail">
			<div class="centered">
				<img src="${pageContext.request.contextPath}/utilize/images/Wallpapers/camera.jpg">
			</div>
		</div>
	</div>
	
	<div class="thumbnail-wrapper">
		<div class="thumbnail">
			<div class="centered">
				<img src="${pageContext.request.contextPath}/utilize/images/Wallpapers/04.jpg">
			</div>
		</div>
	</div>
</div>
</body>
</html>