<%@page import="vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	MemberVo vo = (MemberVo) request.getAttribute("memberVo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function modify() {
		location.href="/member/modify/check/password"
	}
	
	function modifyPwd() {
		location.href="/member/modifyPwd"
	}
	
	function leave() {
		location.href="/member/leave"
	}
	
	function goIndex() {
		location.href="/"
	}
</script>

</head>
<body>
<table>
	<tr>
		<td>
			<div>
				회원번호 : <%=vo.getMber_seq() %>
			</div>
		</td>
	</tr>
	
	<tr>
		<td>
			<div>
				아이디 : <%=vo.getId() %>
			</div>
		</td>
	</tr>
	
	<tr>
		<td>
			<div>
				이름 : <%=vo.getNm() %>
			</div>
		</td>
	</tr>
	
	<tr>
		<td>
			<div>
				휴대전화번호 : <%=vo.getMoblphon() %>
			</div>
		</td>
	</tr>
	
	<tr>
		<td>
			<div>
				가입일시 : <%=vo.getDttm() %>
			</div>
		</td>
	</tr>
</table>

<button onclick="modify()">회원정보수정</button>
<button onclick="modifyPwd()">비밀번호변경</button>
<button onclick="leave()">회원탈퇴</button>
<button onclick="goIndex()">메인으로</button>

</body>
</html>