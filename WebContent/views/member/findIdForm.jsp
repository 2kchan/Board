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
	function findId() {
		var name = $('#name').val();
		var mobile = $('#mobile').val();
		
		if (!name) {
			alert("이름을 입력해 주세요.")
			$('#name').focus();
			return;
		}
		
		if (!mobile) {
			alert("휴대전화번호를 입력해 주세요.")
			$('#mobile').focus();
			return;
		}
	
		var regExpName = new RegExp("<%=Constants.MEMBER_REGEXP_NAME%>", "g");
		if (regExpName.exec(name) == null) {
			alert('이름는 2~20자의 한글로 입력해 주세요');
			$('#name').val('');
			$('#name').focus();
			return;
		}
		
		var regExpMobile = new RegExp("<%=Constants.MEMBER_REGEXP_MOBILE%>", "g");
		if (regExpMobile.exec(mobile) == null) {
			alert('잘못된 휴대전화번호 형식입니다');
			$('#mobile').val('');
			$('#mobile').focus();
			return;
		}
 		
		$('#findForm').attr('action', '/member/find/id/result');
		 $('#findForm').submit(); 
	}
</script>
</head>
<body>

	<form method="post" id="findForm">
		<div>
			이름 <input type="text" id="name" name="nm" placeholder="2~20자의 한글" />
		</div>

		<div>
			휴대전화번호 <input type="text" id="mobile" name="moblphon"
				placeholder="-를 제외한 숫자" />
		</div>

	</form>
	<button onclick="findId()">아이디찾기</button>
	<button onclick="location.href='/'">취소</button>

</body>
</html>