<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Write Your Story! - Story Blog</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/utilize/css/Story_Blog.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
		
	<style type="text/css">
		.font-montserrat-c {
			font-family: 'Montserrat', sans-serif;
		}
		.i-mar-size {
			 margin-left: 27%;
			 font-size: 18px;
		}
	</style>

	<script type="text/javascript">
		function diary_change() {
			if (document.write.sel_diary.options[document.write.sel_diary.selectedIndex].value == 'Select') {
				document.write.d_diary.disabled = true;
				document.write.d_diary.value = "";
			}
			if (document.write.sel_diary.options[document.write.sel_diary.selectedIndex].value == 'Direct') {
				document.write.d_diary.disabled = false;
				document.write.d_diary.value = "";
				document.write.d_diary.focus();
			} else {
				document.write.d_diary.disabled = true;
				document.write.d_diary.value = document.write.sel_diary.options[document.write.sel_diary.selectedIndex].value;
			}
		}
		
	</script>
	
	<!-- SmartEditor�� ����ϱ� ���ؼ� ���� js������ �߰� (��� Ȯ��) -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/utilize/SE2/js/service/HuskyEZCreator.js" charset="EUC-KR"></script>
	<!-- jQuery�� ����ϱ����� jQuery���̺귯�� �߰� -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	
</head>
<%@include file="/utilize/common/header.jsp" %>
<body>
<div style="margin-top: 39px; background: #f1f1f1; width: 100%;" class="font-montserrat-c">
		
	<!-- form (diary_write) -->
	<form action="${pageContext.request.contextPath}/diary/diary_writePro" enctype="multipart/form-data" method="post" name="write" onsubmit="return check(this)">
	<div class="w3-container" style="background: #f3f1f1;">
		
		<!-- table1 (name, subject..)  -->
		<center class="story-center-margin">
		<div class="w3-container story-border-nobot story-nobot-round" style="margin-top: 3%; padding-top:1%;  width: 50%; background-color: #FFF5FA;">	
			<table class="w3-table " cellspacing="20">        
      				
      			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-user i-mar-size">&nbsp;</i>
						<span style="font-size: 14px;">Writer</span>
					</td>
					<td>
						<input type="text" class="story-input-1 w3-light-gray" name="user_name" value="${s_name}" style="width: 80%;" readonly>
					</td>	
       			</tr>
      				
       			<tr class="w3-margin-top">
      				<td>
						<i class="fas fa-list-ul i-mar-size">&nbsp;</i>
						<span style="font-size: 14px;">Diary</span>
					</td>
					<td>
						<input type="text" class="story-input-1" name="d_diary" style="width: 40%; display: inline-block;" value="" disabled>
						
						<select class="story-select-2" style="width: 40%;" name="sel_diary" onchange="diary_change()">
							<option value= "Select" disabled selected>Select!!</option>
				   			<option value= "Direct">Direct</option>
				   			<c:forEach var="diary" items="${d_diarylist}">
				   			<option value= "${diary.d_diary}">${diary.d_diary}</option>
				   			</c:forEach>
				   		</select>
				   		
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-clock i-mar-size">&nbsp;</i>
						<span style="font-size: 14px;">Date</span>
					</td>
					<td>
						<input type="date" class="story-input-1" name="d_date" style="width: 80%;">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-edit i-mar-size">&nbsp;</i>
						<span style="font-size: 14px;">Subject</span>
					</td>
					<td>
						<input type="text" class="story-input-1" name="subject" style="width: 80%;">
					</td>	
       			</tr>
       			
       		</table>

		</div>
		</center>
		<!-- end. table1 (name, subject..)  -->
		
		<!-- SE2 -->
		<center class="story-center-margin">
		<div class="w3-container story-border-left-right" style="width: 50%; background-color: #FFF5FA;">
			
			<center style="margin: 0 7.5%;">
				<div>
					<textarea id="ir1" name="content" cols="6"></textarea>
				</div>
			</center>

		</div>
		</center>
		<!-- end. SE2 -->
		
		<!-- table2 (file, submit..) -->
		<center class="story-center-margin">
		<div class="w3-container story-border-notop story-notop-round" style="margin-bottom: 3%; padding-bottom: 1%; width: 50%; background-color: #FFF5FA;">
			
			<div style="margin:0 5% 0 7.5%;">
				<table class="w3-table" cellspacing="20">        
       				<tr class="w3-margin-top">
       					<td style="margin-left: 3%;">
							<i class="far fa-file-image i-mar-size">&nbsp;</i>
							<span style="font-size: 14px;">Image</span>
						</td>
						<td style="display: inline-block;">
							<input type="file" name="filename1" style="font-size: 9pt; width: 30%;">
							<input type="file" name="filename2" style="font-size: 9pt; width: 30%;">
							<input type="file" name="filename3" style="font-size: 9pt; width: 30%;">
							<input type="file" name="filename4" style="font-size: 9pt; width: 30%;">
							<input type="file" name="filename5" style="font-size: 9pt; width: 30%;">
							<input type="file" name="filename6" style="font-size: 9pt; width: 30%;">
						</td>
					</tr>
				</table>
			</div>
			
			<div class="w3-container w3-right" style="margin: 1% 3% 0% 0%;">
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="user_email" value="${s_email}">
				
				<input type="submit" class="w3-button w3-round w3-gray w3-text-white" value="Write!" >
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel"  OnClick="window.location='${pageContext.request.contextPath}/user/user_main'">
			</div>	
			
       	</div>	
		</center>
		<!-- end. table2 (file, submit..) -->
       	
				
	</div>
	</form>
	<!-- end. form (diary_write) -->
		
	<!-- footer  -->
	<div class="story-footer" style="background: #EAEAEA;">
		<div class="story-copyright">
			&copy; Powered by <a href="${pageContext.request.contextPath}/user/LogoutPro">Story Blog</a>
		</div>
	</div>
	<!-- end. footer -->
	
</div>

<!-- SE2 Setting -->
<script type="text/javascript">
	var oEditors = [];
	
	//�߰� �۲� ���
	//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
	
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "ir1",
		sSkinURI: "${pageContext.request.contextPath}/utilize/SE2/SmartEditor2Skin.html",	
		htParams : {
			bUseToolbar : true,				// ���� ��� ���� (true:���/ false:������� ����)
			bUseVerticalResizer : true,		// �Է�â ũ�� ������ ��� ���� (true:���/ false:������� ����)
			bUseModeChanger : true,			// ��� ��(Editor | HTML | TEXT) ��� ���� (true:���/ false:������� ����)
			//aAdditionalFontList : aAdditionalFontSet,		// �߰� �۲� ���
			fOnBeforeUnload : function(){
				//alert("�Ϸ�!");
			}
		}, //boolean
		fOnAppLoad : function(){
			//���� �ڵ�
			//oEditors.getById["ir1"].exec("PASTE_HTML", ["�ε��� �Ϸ�� �Ŀ� ������ ���ԵǴ� text�Դϴ�."]);
		},
		fCreator: "createSEditor2"
	});
	
	function pasteHTML() {
		var sHTML = "<span style='color:#FF0000;'>�̹����� ���� ������� �����մϴ�.<\/span>";
		oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
	}
	
	function showHTML() {
		var sHTML = oEditors.getById["ir1"].getIR();
		alert(sHTML);
	}
	
	function name() {
		
	}
	
	// * ����: input submit - onclick �� (�̻��)
	function submitContents(elClickedObj) {
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// �������� ������ textarea�� ����˴ϴ�.
		
		// �������� ���뿡 ���� �� ������ �̰����� document.getElementById("ir1").value�� �̿��ؼ� ó���ϸ� �˴ϴ�.
	
		try {
			elClickedObj.form.submit();
		} catch(e) {}
	}
	
	// + �߰�: form - onsubmit �� (���)
	function check(elClickedObj) {
		var form = document.write;
		if(form.sel_diary.options[document.write.sel_diary.selectedIndex].value == 'Select') {
			alert("�ϱ����� �������ּ���."); 
			return false;
		}
		if (document.write.d_diary.disabled = false) {
			return false;
		}
		if(!form.d_date.value) {
			alert("��¥�� �Է����ּ���."); 
			return false;
		}
		if(!form.subject.value) {
			alert("������ �Է����ּ���."); 
			return false;
		}
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		try {
			elClickedObj.form.submit();
		} catch(e) {}
	}
	
	function setDefaultFont() {
		var sDefaultFont = '�ü�';
		var nFontSize = 24;
		oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
	}
</script>
<!-- end. SE2 Setting -->

</body>
</html>