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
	var flag = false;
	
	function initFlag() {
		flag = false;
	}
	
 	function checkId() {
		var id = $('#id').val();
		if (!id) {
			alert("아이디를 입력해 주세요.")
			$('#id').focus();
			return;
		}
		
		var regExpId = new RegExp("<%=Constants.MEMBER_REGEXP_ID%>", "g");
		if (regExpId.exec(id) == null) {
			alert('아이디는 4~20자의 영문소문자, 숫자로 입력해 주세요');
			$('#id').val('');
			$('#id').focus();
			return
		}
		
		// ajax 아이디 중복체크
		$.ajax({
			url : "/member/ajaxCheckId"
			, type : "post"
			, dataType : "json"
			, data : {
				id : id
			}
			, error : function() {
				alert("서버와 통신 실패");
			}
			, success : function(data) {
				var result = data.result;
				var msg = data.msg;
				alert(msg);
				
				if(result=='true') {
					flag = true;
				} else {
					initFlag();
				}
			}
		});
	} 
	
	function joinMember() {
		var id = $('#id').val();
		var pwd=$('#pwd').val();
		var confirmPwd = $('#confirmPwd').val();
		var name = $('#name').val();
		var mobile = $('#mobile').val();
		
		if (!id) {
			alert("아이디를 입력해 주세요.")
			$('#id').focus();
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
		
		var regExpId = new RegExp("<%=Constants.MEMBER_REGEXP_ID%>", "g");
		if (regExpId.exec(id) == null) {
			alert('아이디는 4~20자의 영문소문자, 숫자로 입력해 주세요');
			$('#id').val('');
			$('#id').focus();
			return;
		}
		
		var regExpPwd = new RegExp("<%=Constants.MEMBER_REGEXP_PWD%>", "g");
			if (regExpPwd.exec(pwd) == null) {
				alert('비밀번호는 4~20자의 영문소문자, 숫자 특수기호(~,!,@,#,$,%,^,&,*)로 입력해 주세요');
				$('#pwd').val('');
				$('#pwd').focus();
				return
			}
			
			if (pwd != confirmPwd) {
				alert('비밀번호가 일치하지 않습니다');
				$('#confrimPwd').val('');
				$('#confrimPwd').focus();
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
 		if (!flag) {
			alter('아이디 중복확인을 해 주세요')
			return;
		} 
		$('#joinForm').attr('action', '/member/joinProc');
		 $('#joinForm').submit(); 
	}
</script> 
</head>
<body>

		 <form method="post" id="joinForm">
		<div>
			아이디 <input type="text" id="id" name="id"
				placeholder="4~20자의 영문소문자, 숫자" oninput="initFlag()" />
			<button type="button" onclick="checkId()">중복확인</button>
		</div>

		<div>
			비밀번호 <input type="password" id="pwd" name="pwd"
				placeholder="4~20자의 영문, 숫자, 특수기호(~,!,@,#,$,%,^,&,*)" />
		</div>

		<div>
			비밀번호 확인 <input type="password" id="confirmPwd" name="confirmPwd"
				placeholder="비밀번호와 동일하게 입력" />
		</div>

		<div>
			이름 <input type="text" id="name" name="nm" placeholder="2~20자의 한글" />
		</div>

		<div>
			휴대전화번호 <input type="text" id="mobile" name="moblphon"
				placeholder="-를 제외한 숫자" />
		</div>

	</form>
	<button onclick="joinMember()">가입</button>
	<button onclick="location.href='/'">취소</button>
	
	
	

</body>
</html>