<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${s_name}'s Gallery - Story Blog</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<style type="text/css">
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
		
		.font-nanum-coding-c {
			font-family: 'Nanum Gothic Coding', monospace;
		}
	</style>
	<script type="text/javascript">
		function Modal(element) {
			document.getElementById("diary_img").src = element.src;
			document.getElementById("diary_modal").style.display = "block";
			var captionText = document.getElementById("caption");
			captionText.innerHTML = element.alt;
		}
	</script>
</head>

<%@include file="/utilize/common/header.jsp"%>

<body>
<div class="w3-container" style="margin-top: 39px; background: #f1f1f1; width: 100%; padding: 0;">
	
	<c:if test="${count==0}">
	<div class="w3-container w3-white w3-round font-nanum-coding-c">
		<div class="w3-center w3-container story-padding-t16-b24">
			<p>사진이 포함된 일기가 없습니다. 사진을 첨부해서 일기를 써주세요.</p>
			<input type="button" class="w3-light-gray w3-small w3-button" value="뒤로" onclick="history.go(-1)">
			<input type="button" class="w3-light-gray w3-small w3-button" value="글쓰기" onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_write'">
		</div>
	</div>
	</c:if>
	
	<c:if test="${count!=0}">
	
		
	<c:forEach var="diary" items="${gallery_list}">
	<div style="margin: 1.5% 5%; border: 1.5px solid #FCFCFC;">
	
		<div style="background: #FCFCFC;">
			<div class="w3-container w3-padding-16">
				<span class="w3-left">
					<i class="far fa-edit i-mar-size">&nbsp;</i>
					<span style="font-size: 13pt;" class="font-montserrat-c">Subject:&nbsp;</span>
					<a href="${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&email=${diary.user_email}&pageNum=${currentPage}&type=gallery" 
					class="w3-text-gray w3-hover-text-black font-nanum-coding-c" style="font-size: 12.5pt; text-decoration: none;">
						${diary.subject}
					</a>					
				</span>
				
				<span class="w3-right">
					<i class="far fa-clock i-mar-size">&nbsp;</i>
					<span style="font-size: 13pt;" class="font-montserrat-c">Date:&nbsp;</span>
					<span class="font-montserrat-c" style="font-size: 12.5pt;">${diary.d_date}</span>
				</span>
				
			</div>
		</div>
		
		
		<div class="w3-container w3-padding-small">
			<c:if test="${diary.filename1!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename1}" alt="${diary.filename1}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename2!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename2}" alt="${diary.filename2}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename3!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename3}" alt="${diary.filename3}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename4!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename4}" alt="${diary.filename4}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename5!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename5}" alt="${diary.filename5}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
			
			<c:if test="${diary.filename6!=null}">
			<div class="story-third-1 w3-padding-16" style="margin: 0% 1.5%;">
				<div class="story-thumbnail" style="background: #f6f6f6;">
					<div class="story-centered">
						<img src="${pageContext.request.contextPath}/fileSave/${diary.filename6}" alt="${diary.filename6}" class="story-autoimg2" onclick="Modal(this)">
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>
	</c:forEach>
	
	</c:if>
	
	<c:if test="${count!= 0}">
	<div class="w3-center font-montserrat-c" style="background: #FCFCFC; margin: 1.5% 5%; padding: 2px 4px !important;">
		
		<form method="post" action="${pageContext.request.contextPath}/diary/diary_gallery">
			<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="date_opt">
				<option class="w3-text-gray w3-light-gray" selected disabled>Date Select</option>
				<c:forEach var="diary" items="${date_list}">
				<option value="${diary.d_date}">${diary.d_date}</option> 
				</c:forEach>
			</select> 			
			<input type="submit" class="w3-button w3-light-gray font-montserrat-c" value="Search" 
			 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
		</form>
		
		<c:if test="${count>0}">
			<c:if test="${startPage > bottomLine}">
			<a href="diary_gallery?email=${s_email}&pageNum=${startPage - bottomLine}" style="font-size: 11pt;">[이전]</a>
			</c:if>
	
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="diary_gallery?email=${s_email}&pageNum=${i}"> 
				<c:if test="${i != currentPage}">
					[${i}]
				</c:if> 
				<c:if test="${i == currentPage}">
					<font color='orange'>[${i}]</font>
				</c:if>
			</a>
			</c:forEach>
	
			<c:if test="${endPage < pageCount}">
			<a href="diary_gallery?email=${s_email}&pageNum=${startPage + bottomLine}" style="font-size: 11pt;">[다음]</a>
			</c:if>
		</c:if>
	</div>
	</c:if>
	
	<!-- MODAL -->
	<div id="diary_modal" class="w3-modal" style="padding-top: 0" onclick="this.style.display='none'">
		<span class="w3-button w3-black w3-xlarge w3-display-topright">×</span>
		<div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
			<img id="diary_img" class="w3-image" style="height: auto; width: 50%;">
			<p id="caption"></p>
		</div>
	</div>
	<!-- end. MODAL -->
	
	

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