<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
	<title>[Manager] ${user.name}'s Page - Story Blog</title>
	<style type="text/css">
		#font-poiretone {
			font-family: 'Poiret One', cursive;
		}
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
	</style>
</head>
<%@include file="/utilize/common/header.jsp" %>
<body>
<div style="margin-top: 39px; background: #f1f1f1; width: 100%;">
	
	<!-- form (admin_userinfo) -->	
	<form action="${pageContext.request.contextPath}/admin/admin_userinfoPro" enctype="multipart/form-data" method="post" name="admin_userinfo">
	<div class="w3-container" style="background: #f3f1f1;">
		
		<!-- table (info)  -->
		<center class="font-montserrat-c story-center-margin" style="padding: 2.3% 0%;">
		
		<div class="w3-container w3-border w3-round w3-margin" style="margin: 3% 0%; padding-top:1%;  width: 50%; background-color: #FFF5FA;">	
			<table class="w3-table " cellspacing="20" style="margin-left: 5.5%;">        
      			
      			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-envelope i-mar-size">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;">Email</span>
					</td>
					<td>
						<input type="email" class="story-input-1 w3-light-gray" name="email" value="${user.email}" style="width: 80%;" readonly="readonly">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fa-key i-mar-size">&nbsp;&nbsp;</i>	
						<span style="font-size: 14px;">Password</span>
					</td>
					<td>
						<input type="text" class="story-input-1" name="pwd" value="${user.pwd}" style="width: 80%;">
					</td>	
       			</tr>
      			
      			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-id-badge i-mar-size">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;">Name</span>
					</td>
					<td>
						<input type="text" class="story-input-1" name="name" value="${user.name}" style="width: 80%;">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fa-phone i-mar-size">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;">Tel</span>
					</td>
					<td>
						<input type="number" class="story-input-1" name="tel" value="${user.tel}" style="width: 80%;">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fa-birthday-cake i-mar-size">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;">Birthday</span>
					</td>
					<td>
						<input type="date" class="story-input-1" name="birth" value="${user.birth}" style="width: 80%;">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fas fa-sort i-mar-size w3-text-red">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;" title="유저의 메인, 갤러리의 정렬 옵션" class="w3-text-red">Sort</span>
					</td>
					<td>
						<c:if test="${user.sort_option == null}">
						<span>
							<input type="radio" name="sort_option" value="r_date">
							<span style="font-size: 14px;">작성일</span>
						</span>
						&nbsp;
						<span>
							<input type="radio" name="sort_option" value="n_date">
							<span style="font-size: 14px;">일기 기준 날짜</span>
						</span>
						</c:if>
						
						<c:if test="${user.sort_option != null}">
							<c:if test="${user.sort_option eq 'r_date'}">
							<span>
								<input type="radio" name="sort_option" value="r_date" checked="checked">
								<span style="font-size: 14px;">작성일</span>
							</span>
							&nbsp;
							<span>
								<input type="radio" name="sort_option" value="n_date">
								<span style="font-size: 14px;">일기 기준 날짜</span>
							</span>
							</c:if>
							
							<c:if test="${user.sort_option eq 'n_date'}">
							<span>
								<input type="radio" name="sort_option" value="r_date">
								<span style="font-size: 14px;">작성일</span>
							</span>
							&nbsp;
							<span>
								<input type="radio" name="sort_option" value="n_date" checked="checked">
								<span style="font-size: 14px;">일기 기준 날짜</span>
							</span>
							</c:if>
						</c:if>
					
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fa-user-astronaut i-mar-size w3-text-orange">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;" title="권한 설정" class="w3-text-orange">Authority</span>
					</td>
					<td>
						<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="p_level">
							<option class="w3-text-gray" selected>${user.p_level}</option>
							<option value="1(User)">1(User)</option>
							<option value="2(S-User)">2(S-User)</option>
							<c:if test="${s_p_level eq '4(S-Manager)'}">
							<option value="3(Manager)">3(Manager)</option>
							</c:if>
							
						</select> 
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-image i-mar-size">&nbsp;&nbsp;</i>
						<span style="font-size: 14px;">Photo</span>
					</td>
					<td>
						<img src="${pageContext.request.contextPath}/userSave/${user.filename}" style="height:200px; width:200px;">
						<input type="file" class="story-input-1" name="filename" value="${user.filename}" style="width: 80%;">
					</td>	
       			</tr>
       			
       		</table>
       		
       		<div class="w3-container w3-right" style="margin: 1% 3% 1% 0%;">
       			<input type="hidden" name="num" value="${user.num}">
       			<input type="hidden" name="fname" value="${user.filename}">
       			<input type="hidden" name="fsize" value="${user.filesize}">
       			
       		
       			
				<input type="submit" class="w3-button w3-round w3-blue w3-text-white" value="Update">
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel"  OnClick="history.back();">
			</div>
		
		</div>		
		</center>
		<!-- end. table (info)  -->
		
	</div>
	</form>
	<!-- end. form (admin_userinfo) -->		
	
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