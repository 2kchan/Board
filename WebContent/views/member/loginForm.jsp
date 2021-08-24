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
	function login() {
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		
		if (!id) {
			alert('아이디를 입력해 주세요.');
			$('#id').focus();
			return false;
		}
		
		if (!pwd) {
			alert('비밀번호를 입력해주세요.');
			$('#pwd').focus();
			return false;
		}
		
		$('#loginForm').attr('action','/member/loginProc');
		return true;
	}
	
	function findId() {
		location.href = "/member/find/id"
	}
	
	function findPwd() {
		location.href = "/member/find/password"
	}
</script>


</head>
<body>
<h1>로그인</h1>
<form id="loginForm" method="post" onsubmit="return login()">
<div>
	<input type="text" id="id" name="id" placeholder="아이디">
</div>
<div>
	<input type="password" id="pwd" name="pwd" placeholder="비밀번호">  
</div>
<div>
<input type="submit" value="로그인">
</div>
</form>
<button onclick="findId()">아이디찾기</button>
<button onclick="findPwd()">비밀번호찾기</button>


</body>
</html>