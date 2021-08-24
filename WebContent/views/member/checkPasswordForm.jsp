<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBoard</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous">
	</script>
	
<script>
	function validate() {
		var pwd = $('#pwd').val();
		
		if(!pwd) {
			alert('비밀번호를 입력해 주세요.');
			$('pwd').focus();
			return false;
		}
		
		$('#checkPasswordForm').attr('action', '/member/modify');
		return true
	}
</script>

</head>
<body>

<form id="checkPasswordForm" method="post" onsubmit="return validate()">
	<h3>회원정보 수정을 위해 비밀번호를 확인합니다.</h3>
	<div>
		비밀번호 <input type="password" id=pwd name=pwd />
		<input type="submit" value="확인"/>
	</div>

</body>
</html>