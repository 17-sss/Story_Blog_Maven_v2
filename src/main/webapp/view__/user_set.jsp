<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>${name}'s Page - Story Blog</title>
</head>
<body>
<!-- ��ü Ʋ div �� --> 
<div>&nbsp;

<!-- ��� ��, ���̵� �� ���� -->
	<div style="margin-top:54px; margin-left: 10%; margin-bottom: 30px;"><br>
		
		<!-- �� �� ���� -->
		<div style="margin-left: 25%; margin-right: 25%;">	
			
			<!-- MyPage -->
			<div class="w3-card w3-round w3-white">
				<h2 class="w3-center">My Page</h2>
			</div>
			
			<!--����  -->
			<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/story/user_updateUPro">
				<div class="w3-card w3-round w3-white w3-padding">
					
					<div class="w3-row w3-section">
					  <div class="w3-col w3-red w3-round w3-center" style="width:100px; font-size: 9pt;">�̸��� <br>(���� �Ұ�)</div>
					    <div class="w3-rest">
					      <input class="w3-input w3-light-gray"  name="email" type="text" value ="${user.email}" readonly="readonly" style="margin-left: 2%;">
					    </div>
					</div>
			
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px;">�̸�</div>
					    <div class="w3-rest">
					      <input class="w3-input"  name="name" type="text" value="${user.name}" style="margin-left: 2%;">
					    </div>
					</div>
			
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px;">��й�ȣ</div>
					    <div class="w3-rest">
					      <input class="w3-input"  name="pwd" type="text" value="${user.pwd}" style="margin-left: 2%;">
					    </div>
					</div>
			
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px;">��ȭ��ȣ</div>
					    <div class="w3-rest">
					      <input class="w3-input"  name="tel" type="text" value="${user.tel}" style="margin-left: 2%;">
					    </div>
					</div>
			
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px;">����</div>
					    <div class="w3-rest">
					      <input class="w3-input"  name="birth" type="date" value="${user.birth}" style="margin-left: 2%;">
					    </div>
					</div>
					
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px;">���</div>
					    <div class="w3-rest">
					      <select class="w3-input" name="bgimg" style="margin-left: 2%;">
					      	<option value="" disabled selected>${bgimg}</option> 
					      	<option value="BLUE">BLUE</option>
					      	<option value="PINK">PINK</option>
					      </select>
					    </div>
					</div>
					
					<div class="w3-row w3-section">
					  <div class="w3-col w3-blue w3-round w3-center" style="width:100px; margin-right: 3%;">����</div>
					    <div class="w3-rest" >
					      <img src="${pageContext.request.contextPath}/userSave/${user.filename}" style="height:300px; width:300px;">
					    </div>
					    <div class="w3-row w3-section">
					    	<input class="w3-input w3-left" type="file" size="60" maxlength="50" name="filename" style="margin-left: 15%; width: 50%;">
					    </div>
					</div>
					
					<div style="font-size: 14pt; font-weight: bold;">
					    * ������ ���, �ٽ� �α������ּ���.
					</div>
					
					<div class="w3-row w3-section">
					    <div class="w3-rest w3-right">
					      <input type="submit" value="����" class="w3-btn w3-red w3-round w3-padding">
					      <a class="w3-btn w3-red w3-round w3-padding" href="${pageContext.request.contextPath}/story/user_deleteUPro?email=${user.email}&pwd=${user.pwd}">����</a>
						  <input type="hidden" name="email" value="${user.email}">
					      <input type="hidden" name="filename" value="${user.filename}">
					    </div>
					</div>
					
					
				</div>
			</form>
			
				
			<!-- (��) ���� -->
			<%-- <form method="post" style="display: inline-block; margin-left: 10px;" class="w3-right" action="${pageContext.request.contextPath}/story/user_deleteUPro">
				<span class="w3-text-red" style="font-size: 9pt;"><b> * ��ư Ŭ���� �ٷ� Ż��˴ϴ�. </b> </span>
				<input type="submit" class="w3-button w3-red w3-small w3-margin" value="Ż��">
				<!-- hidden����  deleteUro�� �ѱ��!!!  -->
              	<input type="hidden" name="email" value="${user.email}">
				<input type="hidden" name="pwd" value="${user.pwd}">
			</form> --%>
		
		</div>
		<!-- end. �� �� ���� -->
	
	</div>		
	<!-- end. ��� ��, ���̵� �� ���� -->

</div>
<!-- end. ��ü Ʋ div �� --> 
</body>
</html>