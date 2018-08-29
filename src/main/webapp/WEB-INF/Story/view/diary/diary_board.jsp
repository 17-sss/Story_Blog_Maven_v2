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
		
		.diary-hover-text-pink:hover {
			color: #FFA2A2 !important
		}
	</style>
</head>	

<%@include file="/utilize/common/header.jsp"%>
<body>
<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">

	<div class="w3-container">
		
		<div style="margin: 2% 24.5% 2% 25%;">

			<c:if test="${count==0 && search == null}">
			<div class="w3-container w3-white w3-round font-nanum-coding-c">
				<div class="w3-center w3-container story-padding-t16-b24">
					<p>일기가 없습니다. 일기를 써주세요.</p>
					<input type="button" class="w3-light-gray w3-small w3-button" value="뒤로" onclick="history.go(-1)">
					<input type="button" class="w3-light-gray w3-small w3-button" value="글쓰기" onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_write'">
				</div>
			</div>
			</c:if>
			
			<c:if test="${count==0 && search != null}">
			<div class="w3-container w3-white w3-round font-nanum-coding-c">
				<div class="w3-container w3-padding-16">
					<p class="w3-center">
						<span class="w3-text-red">"</span>
						<b>${search}</b>
						<span class="w3-text-red">"</span>
						에 대한 검색결과가 없습니다.
					</p>
					<ul style="font-size: 10pt; margin: 0 10% 0 20%;">
						<li>단어의 철자가 정확한지 확인해 보세요.</li>
						<li>한글을 영어로 혹은 영어를 한글로 입력했는지 확인해 보세요.</li>
						<li>검색 옵션을 변경해서 다시 검색해 보세요.</li>
					</ul>
					<div class="w3-center w3-margin-top">
						<input type="button" class="w3-light-gray w3-small w3-button" value="목록"
						onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&pageNum=${pageNum}'">
						<input type="button" class="w3-light-gray w3-small w3-button" value="글쓰기" onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_write'">
					</div>
				</div>
			</div>
			</c:if>
			
			<!-- 전체보기 -->
			<c:if test="${count!=0}">
			
			<div class="w3-container w3-white w3-round w3-margin-bottom w3-padding-16 w3-center font-montserrat-c">
				<span class="w3-left">List</span>
				<a href="${pageContext.request.contextPath}/diary/diary_board">
					<span class="w3-right fas fa-list-ul diary-hover-text-pink" style="font-size: 15pt; margin: 0.6% 0%; color:#FF4848;" title="ALL"></span>
				</a>
				<c:forEach var="diary" items="${d_diarylist}">
					<input type="button" class="w3-button w3-light-gray" value="${diary.d_diary}"  style="display: inline-block; padding: 6px 9px; font-size: 8pt; margin-bottom: 0.4%;"
						 onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board2?email=${s_email}&d_diary='+encodeURI('${diary.d_diary}')">
					<input type="hidden" value="${diary.d_diary}" name="d_diary">
				</c:forEach>
			</div>
			
			<div class="w3-container w3-white w3-round">
		
				<h6 class="w3-right font-montserrat-c">
					Story: <b>${count}</b>
				</h6>
				
				<table class="w3-table w3-bordered w3-border w3-small font-nanum-coding-c" width="120">
					<tr class="w3-center">
						<td align="center" width="10" class="w3-border w3-center font-montserrat-c" style="padding: 10px 0;"><b>NUM</b></td>
						<td align="center" width="50" class="w3-center w3-border font-montserrat-c" style="padding: 10px 30px;"><b>Subject</b></td>
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c"><b>Diary</b></td>
						
						<!-- [전체] 정렬  -->
						<c:if test="${search == null}">
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c">
							<a href="${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&pageNum=${pageNum}&sort_opt=cd" 
							style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
								<b>Reporting Date</b>
							</a>
						</td>
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c">
							<a href="${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&pageNum=${pageNum}&sort_opt=d" 
							style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
								<b>Date</b>
							</a>
						</td>
						</c:if>
						
						<!-- [검색] 정렬 -->
						<c:if test="${search != null}">
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c">
							<a href="javascript:void(0);" style="text-decoration: none;" class="w3-text-gray w3-hover-text-black"
							onclick="document.location.href=
							'${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search='
							 + encodeURI('${search}') + '&opt=${opt}&pageNum=${pageNum}&sort_opt=cd'">
								<b>Reporting Date</b>
							</a>
						</td>
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c">
							<a href="javascript:void(0);" style="text-decoration: none;" class="w3-text-gray w3-hover-text-black"
							onclick="document.location.href=
							'${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search='
							 + encodeURI('${search}') + '&opt=${opt}&pageNum=${pageNum}&sort_opt=d'">
								<b>Date</b>
							</a>
						</td>
						</c:if>
						
						<!-- end. 정렬 -->
						
					</tr>

					<c:forEach var="diary" items="${d_list}">
					<tr height="15">
						<td align="center" width="10" class="w3-border w3-center" style="padding: 10px 0;">${number}</td>
						<c:set var="number" value="${number-1}" />
						<td align="center" width="50" class="w3-center w3-border">
						
						<c:if test= "${sort_opt eq ''}">
							<c:if test = "${opt == null && search == null}">
								<a href="${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&email=${diary.user_email}&pageNum=${currentPage}"
								style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
									${diary.subject}
								</a>
							</c:if>
							<c:if test = "${opt != null && search != null}">
								<a href="javascript:void(0);" style="text-decoration: none;" class="w3-text-gray w3-hover-text-black"
								onclick="document.location.href=
								'${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&email=${diary.user_email}&search='
								 + encodeURI('${search}') + '&opt=${opt}&pageNum=${currentPage}'">
									${diary.subject}
								</a>
							</c:if>
						</c:if>
						
						<c:if test="${sort_opt != null && sort_opt ne ''}">	
							<c:if test = "${opt == null && search == null}">
								<a href="${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&email=${diary.user_email}&pageNum=${currentPage}&sort_opt=${sort_opt}"
								style="text-decoration: none;" class="w3-text-gray w3-hover-text-black">
									${diary.subject}
								</a>
							</c:if>
							<c:if test = "${opt != null && search != null}">
								<a href="javascript:void(0);" style="text-decoration: none;" class="w3-text-gray w3-hover-text-black"
								onclick="document.location.href=
								'${pageContext.request.contextPath}/diary/diary_content?num=${diary.num}&email=${diary.user_email}&search='
								 + encodeURI('${search}') + '&opt=${opt}&pageNum=${currentPage}&sort_opt=${sort_opt}'">
									${diary.subject}
								</a>
							</c:if>
						</c:if>	
							
						</td>
						<td align="center" width="20" class="w3-center w3-border">${diary.d_diary}</td>
						<td align="center" width="20" class="w3-center w3-border">${diary.d_cdate}</td>
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
						<!-- post방식에선 비활성화(Maybe..) 이미 있는듯..? -->
						<%-- <input type="hidden" name="email" value="${s_email}">	 --%>						
						<input type="submit" class="w3-button w3-light-gray font-montserrat-c" value="Search" 
						 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
					</div>
				</form>
				
				<!-- 전체보기 (페이지)  -->
				<c:if test="${count != 0 && search == null}">
				<div class="w3-center font-montserrat-c w3-padding-16">
					<c:if test="${count>0}">
						<c:if test="${startPage > bottomLine}">		
							<c:if test="${sort_opt eq ''}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage - bottomLine}" style="font-size: 11pt;">[이전]</a>
							</c:if>
							<c:if test="${sort_opt != null && sort_opt ne ''}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage - bottomLine}&sort_opt=${sort_opt}" style="font-size: 11pt;">[이전]</a>
							</c:if>
						</c:if>	
					
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${sort_opt eq ''}">
								<a href="diary_board?email=${s_email}&pageNum=${i}">
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:if>
							
							<c:if test="${sort_opt != null && sort_opt ne ''}">
								<a href="diary_board?email=${s_email}&pageNum=${i}&sort_opt=${sort_opt}">
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='red'>[${i}]</font>
								</c:if>
								</a>
							</c:if>		
						</c:forEach>

						<c:if test="${endPage < pageCount}">
							<c:if test="${sort_opt eq ''}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage + bottomLine}" style="font-size: 11pt;">[다음]</a>
							</c:if>
							<c:if test="${sort_opt != null && sort_opt ne ''}">
								<a href="diary_board?email=${s_email}&pageNum=${startPage + bottomLine}&sort_opt=${sort_opt}" style="font-size: 11pt;">[다음]</a>
							</c:if>
						</c:if>
					</c:if>
					
				</div>
				</c:if>
				
				<!-- 전체보기 [검색] 전용 (페이지) -->
				<c:if test="${count != 0 && search != null}">
				<div class="w3-center font-montserrat-c w3-padding-16">
				
					<c:if test="${count>0}">
						<c:if test="${startPage > bottomLine}">
							<c:if test="${sort_opt eq ''}">
								<a href="javascript:void(0);" style="font-size: 11pt;"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[이전]</a>
							</c:if>
							<c:if test="${sort_opt != null && sort_opt ne ''}">
								<a href="javascript:void(0);" style="font-size: 11pt;"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}&sort_opt=${sort_opt}'">[이전]</a>
							</c:if>		
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test= "${sort_opt eq ''}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${i}'">
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='orange'>[${i}]</font>
								</c:if>
								</a>
							</c:if>
							
							<c:if test= "${sort_opt != null && sort_opt ne ''}">
								<a href="javascript:void(0);"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${i}&sort_opt=${sort_opt}'">
								<c:if test="${i != currentPage}">
									[${i}]
								</c:if> 
								<c:if test="${i == currentPage}">
									<font color='red'>[${i}]</font>
								</c:if>
								</a>
							</c:if>
						</c:forEach>

						<c:if test="${endPage < pageCount}">
							<c:if test= "${sort_opt eq ''}">
								<a href="javascript:void(0);" style="font-size: 11pt;"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[다음]</a>
							</c:if>	
							
							<c:if test= "${sort_opt != null && sort_opt ne ''}">	
								<a href="javascript:void(0);" style="font-size: 11pt;"
								onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}&sort_opt=${sort_opt}'">[다음]</a>
							</c:if>
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