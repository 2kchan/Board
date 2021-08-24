<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function login() {
		location.href="/member/login"
	}
	
	function index() {
		location.href="/"
	}
</script>
</head>
<body>
<h1>비밀번호를 변경하였습니다. 변경된 비밀번호로 로그인 하시기 바랍니다.</h1>

<button onclick="login()">로그인</button>
<button onclick="index()">홈</button>

</body>
</html>