<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>[Manager] Manager Page - Story Blog</title>
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
		/* 유저 전체 선택 */
		function checkAll(){
		      if( $("#td_checkAll").is(':checked') ){
		        $("input[name=checkRow]").prop("checked", true);
		      }else{
		        $("input[name=checkRow]").prop("checked", false);
		      }
		}
	</script>	
	
	<!-- 검색어 X -->
	<c:if test="${search == null}">
	<script type="text/javascript">	
		/* 유저 삭제 (체크된 것 전부) */
		function deleteAction(){
			var checkRow = "";
			$( "input[name='checkRow']:checked" ).each (function (){
				checkRow = checkRow + $(this).val()+"," ;
			});
			checkRow = checkRow.substring(0,checkRow.lastIndexOf(",")); //맨끝 , 지우기
			 
			if(checkRow == ''){
				alert("삭제할 대상을 선택하세요.");
			    return false;
			}
			console.log("### checkRow => {}"+checkRow);
			 
			if(confirm("회원을 삭제 하시겠습니까?")){
				var url = document.location.href='${pageContext.request.contextPath}/admin/admin_user_deletePro?';
				var page = 'pageNum=${pageNum}&';
				var user = 'email=' + checkRow;
				location.href= url + page + user;
			}  
		}
	</script>
	</c:if>
	
	<!-- 검색어 O -->
	<c:if test="${search != null}">
	<script type="text/javascript">	
		/* 유저 삭제 (체크된 것 전부) */
		function deleteAction(){
			var checkRow = "";
			$( "input[name='checkRow']:checked" ).each (function (){
				checkRow = checkRow + $(this).val()+"," ;
			});
			checkRow = checkRow.substring(0,checkRow.lastIndexOf(",")); //맨끝 , 지우기
			 
			if(checkRow == ''){
				alert("삭제할 대상을 선택하세요.");
			    return false;
			}
			console.log("### checkRow => {}"+checkRow);
			 
			if(confirm("정보를 삭제 하시겠습니까?")){
				var url = document.location.href='${pageContext.request.contextPath}/admin/admin_user_deletePro';
				var page = 'pageNum=${pageNum}';
				var search = 'search=' + encodeURI('${search}') + '&opt=${opt}'
				var user = 'email=' + checkRow;
				
				location.href= url+'?'+page+'&'+search+'&'+ user;
			}  
		}
	</script>
	</c:if>
	
</head>
<%@include file="/utilize/common/header.jsp"%>
<body>
<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">

<div class="w3-container">
	<c:if test="${count_user==0}">
	<div style="margin: 2% 24.5% 2% 25%;">
		<div class="w3-container w3-white w3-round">
			<div class="w3-container w3-center story-padding-t16-b24">
				<p>회원이 없습니다.</p>
				<input type="button" class="w3-light-gray w3-small w3-button" value="Back" 
				onclick="history.go(-1);">
			</div>
		</div>
	</div>
	</c:if>

	<c:if test="${count_user!=0}">
	<div style="margin: 2% 18.5% 2% 19%;">
		<div class="w3-container w3-white w3-round w3-padding-16">
			
			<span class="w3-right font-montserrat-c" style="font-size: 10pt;">
				User: <b>${count_user}</b>
			</span>
			
			<c:if test="${search != null}">
			<span class="w3-right font-montserrat-c w3-hover-text-red" style="font-size: 10pt;">
				<a href="admin_page">ALL</a>&nbsp;&nbsp;
			</span>
			</c:if>
			
			<div class="w3-margin-top">
				<input class="w3-small" type="button" value="삭제" onclick="deleteAction();"/>
		
				
				<table class="w3-table w3-bordered w3-border w3-small" width="139">
					<!--tr은 행을 정의 ==tr=아래=> td는 데이터 셀을 정의, th는 제목 셀을 정의-->	
					<tr height="5" style="font-size: 9pt;" class="font-nanum-coding-c w3-gray">
						<td align="center" width="3" style="width: 1%;">
							<input type="checkbox" name="checkAll" id="td_checkAll" onclick="checkAll();"/>
						</td>	
						
						<td align="center" width="3"  class="w3-center w3-border font-montserrat-c" style="width: 1%;">NUM</td>
						<td align="center" width="30" class="w3-center w3-border font-montserrat-c" style="width: 10%;">Email</td>
						<td align="center" width="20" class="w3-center w3-border" style="width: 10%;">이름</td>
						<!-- pwd, filename -->
						<td align="center" width="20" class="w3-center w3-border font-montserrat-c">Tel</td>
						<td align="center" width="20" class="w3-center w3-border">생일</td>
						<td align="center" width="30" class="w3-center w3-border">생성일</td>
						<td align="center" width="3"  class="w3-center w3-border">권한</td>
						<td align="center" width="10" class="w3-center w3-border font-montserrat-c">IP</td>
					</tr>
					
					<c:forEach var="user" items="${u_list}">
					<tr height="5" style="font-size: 9pt;">
						<td align="center" width="3" style="width: 1%;" class="w3-light-gray"> 
							<input type="checkbox" name="checkRow" value="${user.email}" />
						</td>
						
						<td align="center" width="3"  class="w3-center w3-border font-montserrat-c" style="width: 1%;">${number}</td>
						<c:set var="number" value="${number-1}" />
						<td align="center" width="30" class="w3-center w3-border" style="width: 10%;">
							<c:if test="${search == null}">
							<a href="${pageContext.request.contextPath}/admin/admin_userinfo?userN=${user.num}&pageNum=${pageNum}" class="w3-hover-text-gray">${user.email}</a>
							</c:if>
							<c:if test="${search != null}">
							<a href="javascript:void(0);" class="w3-hover-text-gray"
							onclick="document.location.href='${pageContext.request.contextPath}/admin/admin_userinfo?userN=${user.num}&search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${pageNum}'">${user.email}</a>
							</c:if>
						</td>
						<td align="center" width="20" class="w3-center w3-border" style="width: 10%;">${user.name}</td>
						<c:if test="${user.tel != null}">
						<td align="center" width="20" class="w3-center w3-border">${user.tel}</td>
						</c:if>
						<c:if test="${user.tel == null}">
						<td align="center" width="20" class="w3-center w3-border w3-text-gray">미입력</td>
						</c:if>
						<c:if test="${user.birth != null}">
						<td align="center" width="20" class="w3-center w3-border">${user.birth}</td>
						</c:if>
						<c:if test="${user.birth == null}">
						<td align="center" width="20" class="w3-center w3-border w3-text-gray">미입력</td>
						</c:if>
						<td align="center" width="30" class="w3-center w3-border" style="font-size: 9pt;">${user.cdate}</td>
						<td align="center" width="3"  class="w3-center w3-border">${user.p_level}</td>
						<td align="center" width="10" class="w3-center w3-border">${user.ip}</td>
					</tr>
					</c:forEach>
				</table>
				
				
				<form class="w3-white" method="post" action="${pageContext.request.contextPath}/admin/admin_page">
					<div class="w3-center w3-margin-top w3-margin-bottom font-nanum-coding-c">
						<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="opt">
							<option class="w3-text-gray" disabled>Search..</option>
							<option value="EN" selected>이메일 + 이름</option>
							<option value="E">이메일</option>
							<option value="N">이름</option>
						</select> 
						<input type="text" class="w3-border" placeholder="유저 검색" name="search" style="padding: 0px; font-size: 10pt;">				
						<input type="submit" class="w3-button w3-light-gray font-montserrat-c" value="Search" 
						 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
					</div>
				</form>
				
				<!-- 페이징 - 검색 X  -->
				<c:if test="${count_user != 0 && search == null}">
				<div class="w3-center font-montserrat-c w3-padding-16">
					<c:if test="${count_user > 0}">
						<c:if test="${startPage > bottomLine}">		
							<a href="admin_page?pageNum=${startPage - bottomLine}" style="font-size: 11pt;">[이전]</a>
						</c:if>	
					
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<a href="admin_page?pageNum=${i}">
							<c:if test="${i != currentPage}">
								[${i}]
							</c:if> 
							<c:if test="${i == currentPage}">
								<font color='orange'>[${i}]</font>
							</c:if>
							</a>
						</c:forEach>

						<c:if test="${endPage < pageCount}">
							<a href="admin_page?pageNum=${startPage + bottomLine}" style="font-size: 11pt;">[다음]</a>
						</c:if>
					</c:if>
				</div>
				</c:if>
				
				<!-- 페이징 - 검색 O  -->
				<c:if test="${count_user != 0 && search != null}">
				<div class="w3-center font-montserrat-c w3-padding-16">
					<c:if test="${count_user>0}">
						<c:if test="${startPage > bottomLine}">
							<a href="javascript:void(0);" style="font-size: 11pt;"
							onclick="document.location.href='${pageContext.request.contextPath}/admin/admin_page?search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[이전]</a>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}">.
							<a href="javascript:void(0);"
							onclick="document.location.href='${pageContext.request.contextPath}/admin/admin_page?search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${i}'">
							<c:if test="${i != currentPage}">
								[${i}]
							</c:if> 
							<c:if test="${i == currentPage}">
								<font color='red'>[${i}]</font>
							</c:if>
							</a>	
						</c:forEach>

						<c:if test="${endPage < pageCount}">
							<a href="javascript:void(0);" style="font-size: 11pt;"
							onclick="document.location.href='${pageContext.request.contextPath}/admin/admin_page?search=' + encodeURI('${search}') + '&opt=${opt}&pageNum=${startPage + bottomLine}'">[다음]</a>	
						</c:if>
					</c:if>
					
				</div>
				</c:if>
				
			</div>
		</div>
	</div>	
	</c:if>
	
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