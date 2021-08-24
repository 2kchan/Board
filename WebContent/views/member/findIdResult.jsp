<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%String id = (String)request.getAttribute("id"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<h1>조회하신 아이디는 <%=id %> 입니다</h1>
<button onclick="location.href='/member/login'">로그인</button>
<button onclick="location.href='/'">홈</button>
</body>
</html>