<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${s_name}'s Story - Story Blog</title>
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
	<script type="text/javascript">
		function Modal(element) {
			document.getElementById("diary_img").src = element.src;
			document.getElementById("diary_modal").style.display = "block";
			var captionText = document.getElementById("caption");
			captionText.innerHTML = element.alt;
		}
		

		function delete_submit() {
			var check_delete=confirm('삭제하시겠습니까?');
			
			return check_delete;
		}
	</script>
</head>
<%@include file="/utilize/common/header.jsp"%>
<body>
	<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">
		<div class="w3-container">
			<div style="margin: 1.8% 17.5% 2% 18%;">	
			
				<div class="w3-container w3-white w3-padding-16">
					
					
					<div class="w3-container w3-white">
						<div style="margin: 0 3%;">
							<div>
								<p class="story-content-15">
									<i class="fas fa-list-ul i-mar-size">&nbsp;</i>
									<span style="font-size: 11pt;" class="font-montserrat-c">Diary</span>
								</p>
								<p class="story-content-85">
									<a class="w3-text-gray w3-hover-text-light-gray font-nanum-coding-c" 
									href="javascript:void(0);"
									onclick="document.location.href= '${pageContext.request.contextPath}/diary/diary_board2?email=${s_email}&d_diary=' + encodeURI('${diary.d_diary}')">
										${diary.d_diary}
									</a>
								</p>
							</div>
						</div>
					</div>
					
					<div class="w3-container w3-white">
						<div style="margin: 0 3%;">	
							<div>
								<p class="story-content-15">
									<i class="far fa-edit i-mar-size">&nbsp;</i>
									<span style="font-size: 11pt;" class="font-montserrat-c">Subject</span>
								</p>
								<p class="story-content-85">
									<span class="w3-text-gray font-nanum-coding-c">
										${diary.subject}
									</span>
								</p>
							</div>
						</div>
					</div>		
					
					<div class="w3-container w3-white">
						<div style="margin: 0 3%;">			
							<div>
								<p class="story-content-15">
									<i class="far fa-clock i-mar-size">&nbsp;</i>
									<span style="font-size: 11pt;" class="font-montserrat-c">Date</span>
								</p>
								<p class="story-content-85">
									<span class="w3-text-gray font-nanum-coding-c">
										${diary.d_date}
									</span>
								</p>
							</div>
						</div>
					</div>
					
					<c:if test="${diary.content ne '<p><br></p>'}">
					<hr>
					<div class="w3-container w3-white">
						<div style="margin: 0 3%;">			
							<div>
								<p class="story-content-15">
									<i class="far fa-star i-mar-size">&nbsp;</i>
									<span style="font-size: 11pt;" class="font-montserrat-c">Content</span>
								</p>
								<span class="story-content-85">
									${diary.content}
								</span>
							</div>
						</div>
					</div>
					</c:if>
					
					<c:if test="${diary.filename1!=null || diary.filename2!=null || diary.filename3!=null || diary.filename4!=null || diary.filename5!=null || diary.filename6!=null}">
					<hr>
					<div class="w3-container w3-white">
						<div style="margin: 0 3%;">			
							<div>
								<p style="padding: 0 8px;">
									<i class="far fa-images i-mar-size">&nbsp;</i>
									<span style="font-size: 11pt;" class="font-montserrat-c">Picture</span>
								</p>
								
								<c:if test="${diary.filename1!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename1}" alt="${diary.filename1}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
								<c:if test="${diary.filename2!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename2}" alt="${diary.filename2}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
								<c:if test="${diary.filename3!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename3}" alt="${diary.filename3}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
								<c:if test="${diary.filename4!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename4}" alt="${diary.filename4}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
								<c:if test="${diary.filename5!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename5}" alt="${diary.filename5}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
								<c:if test="${diary.filename6!=null}">
								<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
									<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
										<div class="story-centered">
											<img src="${pageContext.request.contextPath}/fileSave/${diary.filename6}" alt="${diary.filename6}" class="story-autoimg2" onclick="Modal(this)">
										</div>
									</div>
								</div>
								</c:if>
								
							</div>
						</div>
					</div>
					</c:if>
					
					<hr>
					<div class="w3-center w3-padding-16">
						<!-- 목록 (조건부) -->
						
						<!-- 전체  -->
						<c:if test="${d_diary == null && opt == null && search == null && type ne 'gallery'}">
						<input type="button" class="w3-yellow w3-small w3-button" value="목록" 
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&pageNum=${pageNum}'">
						</c:if>
						<c:if test="${d_diary == null && opt != null && search != null}">
						<input type="button" class="w3-yellow w3-small w3-button" value="목록" 
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search='+ encodeURI('${search}') +'&opt=${opt}&pageNum=${pageNum}'">
						</c:if>
					
						<!-- 개별 -->
						<c:if test="${d_diary != null && opt == null && search == null}">
						<input type="button" class="w3-yellow w3-small w3-button" value="목록" 
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board2?email=${s_email}&d_diary='+ encodeURI('${d_diary}') + '&pageNum=${pageNum}'">
						</c:if>
						<c:if test="${d_diary != null && opt != null && search != null}">
						<input type="button" class="w3-yellow w3-small w3-button" value="목록" 
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board2?email=${s_email}&d_diary='+ encodeURI('${d_diary}') + '&search=' 
						+ encodeURI('${search}') +'&opt=${opt}&pageNum=${pageNum}'">
						</c:if>
						
						<!-- 갤러리 -->
						<c:if test="${type eq 'gallery'}">
						<input type="button" class="w3-yellow w3-small w3-button" value="목록" 
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_gallery?email=${s_email}&pageNum=${pageNum}'">
						</c:if>
						
						<!--end. 목록 (조건부) -->
						
						<!-- 글쓰기 -->
						<input type="button" class="w3-light-gray w3-small w3-button" value="글쓰기" onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_write'">
						
						<!-- 수정 -->
						<form method="post" action="${pageContext.request.contextPath}/diary/diary_update" style="display: inline-block;">
							<input type="submit" class="w3-blue w3-small w3-button" value="수정">
							<input type="hidden" name="email" value="${diary.user_email}">
							<input type="hidden" name="num" value="${diary.num}">
							<input type="hidden" name="pageNum" value="${pageNum}">
							<input type="hidden" name="not_in_d_diary" value="${diary.d_diary}">
							<c:if test = "${d_diary != null}">
							<input type="hidden" name="d_diary" value="${diary.d_diary}">
							</c:if>
							<c:if test = "${search != null && opt != null}">
							<input type="hidden" name="search" value="${search}">
							<input type="hidden" name="opt" value="${opt}">
							</c:if>
						</form>	
						
						<!-- 삭제 -->
						<form method="post" action="${pageContext.request.contextPath}/diary/diary_deletePro" style="display: inline-block;" onsubmit="return delete_submit()">
							<input type="submit" class="w3-red w3-small w3-button" value="삭제">
							<input type="hidden" name="email" value="${diary.user_email}">
							<input type="hidden" name="num" value="${diary.num}">
							<input type="hidden" name="pageNum" value="${pageNum}">
               				<c:if test = "${d_diary != null}">
							<input type="hidden" name="d_diary" value="${diary.d_diary}">
							</c:if>
							<c:if test = "${search != null && opt != null}">
							<input type="hidden" name="search" value="${search}">
							<input type="hidden" name="opt" value="${opt}">
							</c:if>
						</form>
					</div>

				</div>	
				
			</div>
		</div>
		
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