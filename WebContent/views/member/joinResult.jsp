<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String id = (String) request.getAttribute("id");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBoard</title>
<script>
	function login() {
		location.href = "/member/login"
	}

	function index() {
		location.href = "/"
	}
</script>

</head>
<body>
	<h1>회원 가입을 완료하였습니다.</h1>
	<h3>회원 가입 아이디 : <%=id %></h3>
	<div>
		<button onclick="login()">로그인</button>
		<button onclick="index()">홈</button>
	</div>
</body>
</html>