<%@page import="common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstanc();
	String mber_seq = lm.getMemberSequence(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	function join() {
		location.href = "/member/join"
	}
	
	function login() {
		location.href = "/member/login"
	}
	
	function logout() {
		location.href = "/member/logout"
	}
	
	function detail() {
		location.href = "/member/detail"
	}
	
	function list() {
		location.href = "/board/list?pn=1&sf=0&sk=&sort=1"
	}
	
</script>

</head>
<body>
<h1>JSP 게시판!</h1>

<%if (mber_seq==null) {%>
	<button onclick="join()">회원가입</button>
	<button onclick="login()">로그인</button>
<%} else { %>
<div>
	<h1>로그인된 회원번호 : <%=mber_seq %></h1>
</div>
	<button onclick="logout()">로그아웃</button>
	<button onclick="detail()">회원정보</button>
<%} %>
<button onclick="list()">게시판</button>

</body>
</html>



