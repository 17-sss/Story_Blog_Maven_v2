<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function email_change() {
		if (document.join.email.options[document.join.email.selectedIndex].value == '0') {
			document.join.email2.disabled = true;
			document.join.email2.value = "";
		}
		if (document.join.email.options[document.join.email.selectedIndex].value == '9') {
			document.join.email2.disabled = false;
			document.join.email2.value = "";
			document.join.email2.focus();
		} else {
			document.join.email2.disabled = true;
			document.join.email2.value = document.join.email.options[document.join.email.selectedIndex].value;
		}
	}
</script>
</head>
<body>


<form name="join">      
	<input type="text" name="email1" value="�̸���" onfocus="this.value='';">   @
	<input type="text" name="email2" value="" disabled >

	<select name="email" onchange="email_change()">
    	<option value="0" >�����ϼ���</option>
    	<option value="9">�����Է�</option>
 	   <option value="naver.com">naver.com</option>
 	   <option value="nate.com">nate.com</option> 
   </select>

</form>  
</body>
</html>