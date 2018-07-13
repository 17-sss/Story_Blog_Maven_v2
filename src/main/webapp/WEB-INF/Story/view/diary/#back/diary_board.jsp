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
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

	<style type="text/css">
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
		
		.font-nanum-coding-c {
			font-family: 'Nanum Gothic Coding', monospace;
		}
		
		.i-mar-size {
			margin-left: 27%;
			font-size: 18px;
		}
	</style>

<%@include file="/utilize/common/header.jsp"%>
<body>
	<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">

		<div class="w3-container">
			
			<div class="w3-container" style="margin: 2% 25% 2% 25.5%;">

				<c:if test="${count==0 && count2==0}">
				<div class="w3-container w3-white w3-round">
					<div class="w3-center w3-container">
						<h4>일기가 없습니다. 일기를 써주세요.</h4>
					</div>
				</div>
				</c:if>
				
				<!-- 전체보기 -->
				<c:if test="${count!=0 && d_diary==null}">
				
				<div class="w3-container w3-white w3-round w3-margin-bottom w3-padding-16 w3-center font-montserrat-c">
					<span class="w3-left">List</span>
					<a href="${pageContext.request.contextPath}/diary/diary_board">
						<span class="w3-right fas fa-list-ul w3-hover-text-gray" style="font-size: 15pt; margin: 0.6% 0%;" title="ALL"></span>
					</a>
					<c:forEach var="diary" items="${d_diarylist}">
						<input type="button" class="w3-button w3-light-gray" value="${diary.d_diary}"  style="display: inline-block; padding: 6px 9px; font-size: 8pt; margin-bottom: 0.4%;"
							 onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary='+encodeURI('${diary.d_diary}')">
						<input type="hidden" value="${diary.d_diary}" name="d_diary">
					</c:forEach>
				</div>
				
				<div class="w3-container w3-white w3-round">
			
					<h6 class="w3-right font-montserrat-c">
						Story: <b>${count}</b>
					</h6>
					
					<table class="w3-table w3-bordered w3-border w3-small font-nanum-coding-c" width="100">
						<tr class="w3-center">
							<td align="center" width="10" class="w3-border w3-center font-montserrat-c" style="padding: 10px 0;"><b>NUM</b></td>
							<td align="center" width="50" class="w3-center w3-border font-montserrat-c" style="padding: 10px 30px;"><b>Subject</b></td>
							<td align="center" width="20" class="w3-center w3-border font-montserrat-c"><b>Diary</b></td>
							<td align="center" width="20" class="w3-center w3-border font-montserrat-c"><b>Date</b></td>
						</tr>

						<c:forEach var="diary" items="${d_list}">
						<tr height="15">
							<td align="center" width="10" class="w3-border w3-center" style="padding: 10px 0;">${number}</td>
							<c:set var="number" value="${number-1}" />
							<td align="center" width="50" class="w3-center w3-border">
								<a href="${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&pageNum=${currentPage}"
								style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
									${diary.subject}
								</a>
							</td>
							<td align="center" width="20" class="w3-center w3-border">${diary.d_diary}</td>
							<td align="center" width="20" class="w3-center w3-border">${diary.d_date}</td>
						</tr>
						</c:forEach>
					</table>
					
					<!-- 전체 검색 -->
					<form class="w3-white" method="post" action="${pageContext.request.contextPath}/diary/diary_board">
						<div class="w3-center w3-margin-top w3-margin-bottom font-nanum-coding-c">
							<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="opt">
								<option class="w3-text-gray" disabled>Search..</option>
								<option value="SC" selected>제목 + 내용</option>
								<option value="S">제목</option>
								<option value="C">내용</option>
							</select> 
							<input type="text" class="w3-border" placeholder="전체 검색" name="search" style="padding: 0px; font-size: 10pt;">
							<!-- post방식에선 비활성화(Maybe..)-->
							<%-- <input type="hidden" name="email" value="${s_email}">	 --%>						
							<input type="submit" class="w3-button w3-light-gray font-montserrat-c" value="Search" 
							 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
						</div>
					</form>
					
					<!-- 전체보기 (페이지)  -->
					<c:if test="${count != 0 && search == null && d_diary==null}">
					<div class="w3-center w3-white w3-margin">
						<c:if test="${count>0}">
							<c:if test="${startPage > bottomLine}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage - bottomLine}">[이전]</a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a href="diary_board?email=${s_email}&pageNum=${i}"> 
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:forEach>

							<c:if test="${endPage < pageCount}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage + bottomLine}">[다음]</a>
							</c:if>
						</c:if>
					</div>
					</c:if>
					
					<!-- 전체보기 [검색] 전용 (페이지) -->
					<c:if test="${count != 0 && search != null && d_diary==null}">
					<div class="w3-center w3-white w3-margin">
					
						<c:if test="${count>0}">
							<c:if test="${startPage > bottomLine}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[이전]</a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${i}'">
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:forEach>

							<c:if test="${endPage < pageCount}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[다음]</a>
							</c:if>
						</c:if>
						
					</div>
					</c:if>

				</div>
				</c:if>
				
				<!-- ############################ #### ############################ -->
				<!-- ############################ 경계선 ############################ -->
				<!-- ############################ #### ############################ -->
				
				<!-- 일기장 개별  -->
				<c:if test="${count2!=0}">
				
				<div class="w3-container w3-white w3-round w3-margin-bottom w3-padding-16 w3-center font-montserrat-c">
					<span class="w3-left">List</span>
					<a href="${pageContext.request.contextPath}/diary/diary_board">
						<span class="w3-right fas fa-list-ul w3-hover-text-gray" style="font-size: 15pt; margin: 0.6% 0%;" title="ALL"></span>
					</a>
					<c:forEach var="diary" items="${d_diarylist}">
						<input type="button" class="w3-button w3-light-gray" value="${diary.d_diary}"  style="display: inline-block; padding: 6px 9px; font-size: 8pt;"
							 onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary='+encodeURI('${diary.d_diary}')">
						<input type="hidden" value="${diary.d_diary}" name="d_diary">
					</c:forEach>
				</div>
					
				<div class="w3-container w3-white w3-round">
			
					<h6 class="w3-right font-montserrat-c">
						Story: <b>${count2}</b>
					</h6>
					
					<table class="w3-table w3-bordered w3-border w3-small font-nanum-coding-c" width="100">
						<tr class="w3-center">
							<td align="center" width="10" class="w3-border w3-center font-montserrat-c" style="padding: 10px 0;"><b>NUM</b></td>
							<td align="center" width="50" class="w3-center w3-border font-montserrat-c" style="padding: 10px 30px;"><b>Subject</b></td>
							<td align="center" width="20" class="w3-center w3-border font-montserrat-c"><b>Diary</b></td>
							<td align="center" width="20" class="w3-center w3-border font-montserrat-c"><b>Date</b></td>
						</tr>

						<c:forEach var="diary" items="${d_list}">
						<tr height="15">
							<td align="center" width="10" class="w3-border w3-center" style="padding: 10px 0;">${number2}</td>
							<c:set var="number2" value="${number2-1}" />
							<td align="center" width="50" class="w3-center w3-border">
								<a href="${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&pageNum=${currentPage}"
								style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
									${diary.subject}
								</a>
							</td>
							<td align="center" width="20" class="w3-center w3-border">${diary.d_diary}</td>
							<td align="center" width="20" class="w3-center w3-border">${diary.d_date}</td>
						</tr>
						</c:forEach>
					</table>
					
					<!-- 일기장 내에서 검색 -->
					<form class="w3-white" method="post" action="${pageContext.request.contextPath}/diary/diary_board">
						<div class="w3-center w3-margin-top w3-margin-bottom font-nanum-coding-c">
							<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="opt">
								<option class="w3-text-gray" disabled>Search..</option>
								<option value="SC" selected>제목 + 내용</option>
								<option value="S">제목</option>
								<option value="C">내용</option>
							</select> 
							<input type="text" class="w3-border" placeholder="'${d_diary}'에서 검색" name="search" style="padding: 0px; font-size: 10pt;">
							<!-- post방식에선 비활성화(Maybe..)-->
							<%-- <input type="hidden" value="${d_diary}" name="d_diary"> --%>
							<%-- <input type="hidden" value="${s_email}" name="email"> --%>
							<input type="submit" class="w3-button w3-light-gray font-montserrat-c" value="Search" 
							 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
						</div>
					</form>
					
					<!-- 일기장 개별 (페이지)  -->
					<c:if test="${count2 != 0 && search == null}">
					<div class="w3-center w3-white w3-margin">
						<c:if test="${count2>0}">
							<c:if test="${startPage > bottomLine}">
								<a href="javascript:void(0);" 
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&pageNum=${startPage - bottomLine}'">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage2}">	
								<a href="javascript:void(0);" 
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&pageNum=${i}'"> 
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:forEach>

							<c:if test="${endPage2 < pageCount2}">
								<a href="javascript:void(0);" 
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&pageNum=${startPage + bottomLine}'">[다음]</a>
							</c:if>
						</c:if>
					</div>
					</c:if>
					
					
					<!-- 일기장 개별 [검색] 전용 (페이지) -->
					<c:if test="${count2 != 0 && search != null}">
					<div class="w3-center w3-white w3-margin">
						<c:if test="${count2>0}">
							<c:if test="${startPage > bottomLine}">
								<a href="javascript:void(0);" 
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage - bottomLine}'">[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage2}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${i}'"> 
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:forEach>

							<c:if test="${endPage2 < pageCount2}">
								<a href="javascript:void(0);" 
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&d_diary=' + encodeURI('${d_diary}') + '&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[다음]</a>
							</c:if>
						</c:if>
					</div>
					</c:if>

				</div>
				</c:if>

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