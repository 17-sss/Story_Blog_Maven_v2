<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Write a Story! - Story Blog</title>
<style type="text/css">
	.w3-input {
		padding: 8px;
		display: block;
		border: none;
		border-bottom: 1px solid #ccc;
		width: 98%;
	}
</style>

<!-- SmartEditor�� ����ϱ� ���ؼ� ���� js������ �߰� (��� Ȯ��) -->
<script type="text/javascript" src="${pageContext.request.contextPath}/utilize/SE2/js/service/HuskyEZCreator.js" charset="EUC-KR"></script>
<!-- jQuery�� ����ϱ����� jQuery���̺귯�� �߰� -->
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>



</head>
<body>
<div>
	<form id="frm" action="se2test.jsp" enctype="" method="post">
						
		<!-- ���� (SE2) -->
		<div class="w3-row w3-section">
			<div class="w3-content">&nbsp;����
				<textarea  id="ir1" class="w3-input w3-border" rows="10" cols="30" 	name="content">
				</textarea>
			</div>
		</div>
		
		<div class="w3-center" style="margin: 1%;">
			<input class="w3-button w3-white" type="button" onclick="submitContents(this);" value="����" />
			&nbsp;
			<input class="w3-button w3-yellow" type="button" value="���" onClick = "history.back();"/>
		</div>
				
	</form>
</div>
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
		
	function submitContents(elClickedObj) {
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// �������� ������ textarea�� ����˴ϴ�.
		
		// �������� ���뿡 ���� �� ������ �̰����� document.getElementById("ir1").value�� �̿��ؼ� ó���ϸ� �˴ϴ�.
		
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
<!-- end. ��ü Ʋ div �� --> 
</body>
</html>

