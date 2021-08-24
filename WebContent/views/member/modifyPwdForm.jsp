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

	function modifyPwd() {
		
		var oldpwd=$('#oldpwd').val();
		var pwd=$('#pwd').val();
		var confirmPwd = $('#confirmPwd').val();

		if (!oldpwd) {
			alert("패스워드를 입력해 주세요.")
			$('#oldpwd').focus();
			return;
		}
		
		if (!pwd) {
			alert("패스워드를 입력해 주세요.")
			$('#pwd').focus();
			return;
		}
		
		if (!confirmPwd) {
			alert("패스워드를 입력해 주세요.")
			$('#confirmpwd').focus();
			return;
		}
		
		var regExpOldPwd = new RegExp("<%=Constants.MEMBER_REGEXP_PWD%>", "g");
		if (regExpOldPwd.exec(oldpwd) == null) {
			alert('비밀번호는 4~20자의 영문소문자, 숫자 특수기호(~,!,@,#,$,%,^,&,*)로 입력해 주세요');
			$('#oldpwd').val('');
			$('#oldpwd').focus();
			return
		}
		
		var regExpPwd = new RegExp("<%=Constants.MEMBER_REGEXP_PWD%>", "g");
			if (regExpPwd.exec(pwd) == null) {
				alert('비밀번호는 4~20자의 영문소문자, 숫자 특수기호(~,!,@,#,$,%,^,&,*)로 입력해 주세요');
				$('#newpwd').val('');
				$('#newpwd').focus();
				return
			}
			
			if (pwd != confirmPwd) {
				alert('비밀번호가 일치하지 않습니다');
				$('#confrimPwd').val('');
				$('#confrimPwd').focus();
				return;
			}
				
		$('#modifyPwdForm').attr('action', '/member/modifyPwd/Result');
		 $('#modifyPwdForm').submit(); 
	}
</script>
</head>
<body>

	<form method="post" id="modifyPwdForm">
		<div>
			현재비밀번호 <input type="password" id="oldpwd" name="oldpwd"
				placeholder="4~20자의 영문, 숫자, 특수기호(~,!,@,#,$,%,^,&,*)" />
		</div>
		
		<div>
			새비밀번호 <input type="password" id="pwd" name="pwd"
				placeholder="4~20자의 영문, 숫자, 특수기호(~,!,@,#,$,%,^,&,*)" />
		</div>

		<div>
			새비밀번호 확인 <input type="password" id="confirmPwd" name="confirmPwd"
				placeholder="비밀번호와 동일하게 입력" />
		</div>

	</form>
	<button onclick="modifyPwd()">비밀번호변경</button>
	<button onclick="location.href='/'">취소</button>

</body>
</html>