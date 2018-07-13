<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Update Your Story! - Story Blog</title>
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
			if (document.update.sel_diary.options[document.update.sel_diary.selectedIndex].value == 'Select') {
				document.update.d_diary.disabled = true;
				document.update.d_diary.value = "";
			}
			if (document.update.sel_diary.options[document.update.sel_diary.selectedIndex].value == 'Direct') {
				document.update.d_diary.disabled = false;
				document.update.d_diary.value = "";
				document.update.d_diary.focus();
			}
			
			else {
				document.update.d_diary.disabled = true;
				document.update.d_diary.value = document.update.sel_diary.options[document.update.sel_diary.selectedIndex].value;
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
		
	<!-- form (diary_update) -->
	<form action="${pageContext.request.contextPath}/diary/diary_updatePro" enctype="multipart/form-data" method="post" name="update" onsubmit="return check(this)">
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
						<input type="text" class="story-input-1" name="d_diary" style="width: 40%; display: inline-block;" value="${diary.d_diary}" disabled>
						
						<select class="story-select-2" style="width: 40%;" name="sel_diary" onchange="diary_change()">
							<c:if test = "${diary.d_diary == null}">
							<option value= "Select" disabled selected>Select!!</option>
							<option value= "Direct">Direct</option>
							</c:if>
							
							<c:if test = "${diary.d_diary != null}">
							<option value= "Select" disabled>Select!!</option>
							<option value= "Direct">Direct</option>
							<option value= "${diary.d_diary}" selected>${diary.d_diary}</option>
							</c:if>
				   			
				   			<c:forEach var="dlist" items="${d_diarylist}">
				   			<option value= "${dlist.d_diary}">${dlist.d_diary}</option>
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
						<input type="date" class="story-input-1" name="d_date" style="width: 80%;" value="${diary.d_date}">
					</td>	
       			</tr>
       			
       			<tr class="w3-margin-top">
      				<td>
						<i class="far fa-edit i-mar-size">&nbsp;</i>
						<span style="font-size: 14px;">Subject</span>
					</td>
					<td>
						<input type="text" class="story-input-1" name="subject" style="width: 80%;" value="${diary.subject}">
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
					<textarea id="ir1" name="content" cols="6">${diary.content}</textarea>
				</div>
			</center>

		</div>
		</center>
		<!-- end. SE2 -->
		
		<!-- table2 (file, submit..) -->
		<center class="story-center-margin">
		<div class="w3-container story-border-notop story-notop-round" style="margin-bottom: 3%; padding-bottom: 1%; width: 50%; background-color: #FFF5FA;">
			
			<div style="margin:0 5% 0 3.5%;">
				<table class="w3-table" cellspacing="20">        
       				<tr class="w3-margin-top">
       					<c:if test="${(diary.filename1 && diary.filename2 && diary.filename3 && diary.filename4 && diary.filename5 && diary.filename6) == null}">
	       					<td style="margin-left: 3%;">
								<i class="far fa-file-image i-mar-size">&nbsp;</i>
								<span style="font-size: 14px;">Image</span>
							</td>
						</c:if>
						<td style="display: inline-block;">
							<!-- ���� 1  -->
							<c:if test="${diary.filename1!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename1}" alt="${diary.filename1}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename1" style="font-size: 9pt;" value="${diary.filename1}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename1==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename1" style="font-size: 9pt;">
							</div>	
							</c:if>
							<!-- ���� 2 -->
							<c:if test="${diary.filename2!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename2}" alt="${diary.filename2}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename2" style="font-size: 9pt;" value="${diary.filename2}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename2==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename2" style="font-size: 9pt;">
							</div>	
							</c:if>
							<!-- ���� 3 -->
							<c:if test="${diary.filename3!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename3}" alt="${diary.filename3}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename3" style="font-size: 9pt;" value="${diary.filename3}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename3==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename3" style="font-size: 9pt;">
							</div>	
							</c:if>
							<!-- ���� 4 -->
							<c:if test="${diary.filename4!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename4}" alt="${diary.filename4}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename4" style="font-size: 9pt;" value="${diary.filename4}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename4==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename4" style="font-size: 9pt;">
							</div>	
							</c:if>
							
							<!-- ���� 5 -->
							<c:if test="${diary.filename5!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename5}" alt="${diary.filename5}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename5" style="font-size: 9pt;" value="${diary.filename5}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename5==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename5" style="font-size: 9pt;">
							</div>	
							</c:if>
							
							<!-- ���� 6 -->
							<c:if test="${diary.filename6!=null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/fileSave/${diary.filename6}" alt="${diary.filename6}" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename6" style="font-size: 9pt;" value="${diary.filename6}">
							</div>
							</c:if>
							
							<c:if test="${diary.filename6==null}">
							<div class="story-third-1 w3-margin-bottom" style="margin: 0% 1.5%;">
								<div class="story-thumbnail w3-round" style="background: #f6f6f6;">
									<div class="story-centered">
										<img src="${pageContext.request.contextPath}/utilize/images/ETC/no_image.png" class="story-autoimg2">
									</div>
								</div>
								<input type="file" name="filename6" style="font-size: 9pt;">
							</div>	
							</c:if>
							
						</td>
					</tr>
				</table>
			</div>
			
			<div class="w3-container w3-right" style="margin: 1% 3% 0% 0%;">
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="email" value="${s_email}">
				<input type="hidden" name="pageNum" value="${pageNum}">
				
				<input type="hidden" name="filename1" value="${diary.filename1}"> <input type="hidden" name="filename2" value="${diary.filename2}">
				<input type="hidden" name="filename3" value="${diary.filename3}"> <input type="hidden" name="filename4" value="${diary.filename4}">
				<input type="hidden" name="filename5" value="${diary.filename5}"> <input type="hidden" name="filename6" value="${diary.filename6}">
				
				<!-- chk_d_diary�� ����(��Ʈ�ѷ�)���� ó�� [����] -->
				<input type="hidden" name="chk_d_diary" value="${chk_d_diary}">
				
				<c:if test="${opt != null && search != null}">
				<input type="hidden" name="search" value="${search}">
				<input type="hidden" name="opt" value="${opt}">
				</c:if>
				
				<input type="submit" class="w3-button w3-round w3-gray w3-text-white" value="Update!" >
				
				<!-- ��ü  -->
				<c:if test="${d_diary == null && opt == null && search == null}">
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel" 
				onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&pageNum=${pageNum}'">
				</c:if>
				<c:if test="${d_diary == null && opt != null && search != null}">
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel" 
				onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&search='+ encodeURI('${search}') +'&opt=${opt}&pageNum=${pageNum}'">
				</c:if>
			
				<!-- ���� -->
				<c:if test="${d_diary != null && opt == null && search == null}">
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel" 
				onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&d_diary='+ encodeURI('${d_diary}') + '&pageNum=${pageNum}'">
				</c:if>
				<c:if test="${d_diary != null && opt != null && search != null}">
				<input type="button" class="w3-button w3-round w3-gray w3-text-white" value="Cancel" 
				onclick="document.location.href='${pageContext.request.contextPath}/diary/diary_content?num=${num}&email=${s_email}&d_diary='+ encodeURI('${d_diary}') + '&search=' 
				+ encodeURI('${search}') +'&opt=${opt}&pageNum=${pageNum}'">
				</c:if>
			</div>	
			
       	</div>	
		</center>
		<!-- end. table2 (file, submit..) -->
       	
				
	</div>
	</form>
	<!-- end. form (diary_update) -->
		
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
		var form = document.update;
		if(form.sel_diary.options[document.update.sel_diary.selectedIndex].value == 'Select') {
			alert("�ϱ����� �������ּ���."); 
			return false;
		}
		if (document.update.d_diary.disabled = false) {
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