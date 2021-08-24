<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="common.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBoard</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script>

	function leave() {
		if (!confirm("정말 회원 탈퇴 하시겠습니까?")) {
			return;
		}
		
		var pwd=$('#pwd').val();

		if (!pwd) {
			alert("패스워드를 입력해 주세요.")
			$('#pwd').focus();
			return;
		}
				
		$('#leaveForm').attr('action', '/member/leaveResult');
		 $('#leaveForm').submit(); 
	}
</script>
</head>
<body>
<h1>회원탈퇴 시 회원정보가 삭제되며, 회원정보는 복구할수 없습니다</h1>

	<form method="post" id="leaveForm">
		<div>
			비밀번호 <input type="password" id="pwd" name="pwd"
				placeholder="4~20자의 영문, 숫자, 특수기호(~,!,@,#,$,%,^,&,*)" />
		</div>
	</form>
	<button onclick="leave()">회원탈퇴</button>
	<button onclick="location.href='/'">취소</button>

</body>
</html>