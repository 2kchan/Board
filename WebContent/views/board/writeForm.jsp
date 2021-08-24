<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String pn = request.getParameter("pn");
  		String sf = request.getParameter("sf");
  	 	String sk = request.getParameter("sk");
   		String sort = request.getParameter("sort");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous">
</script>
<script>
	function cancelArticle() {
		location.href = "/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=" + encodeURI('<%=sk%>') + "&sort=<%=sort%>"
	}
	
	function writeArticle() {
		var sj = $('#sj').val();
		var cntnt = $('#cntnt').val();
		
		if(!sj) {
			alert('제목을 입력해 주세요.');
			$('#sj').focus();
			return;
		}
		
		if(!cntnt) {
			alert('내용을 입력해 주세요.');
			$('#cntnt').focus();
			return;
		}
		
		if(sj.length > 50) {
			alert('제목은 50자 이내로 작성해 주세요.');
			$('#sj').focus();
			return;
		}
		
		$('#writeForm').attr('action', '/board/writeProc');
		$('#writeForm').submit();
	}
</script>
	
</head>
<body>
	<form method="post" id="writeForm">
		<div>
			제목 <input type="text" id="sj" name="sj" maxlength="50"/>
		</div>
		
		<div>
			내용 <textarea name="cntnt" id="cntnt" cols="30" rows="10"></textarea>
		</div>
	</form>
	<button onclick="writeArticle()">저장</button>
	<button onclick="cancelArticle()">취소</button>
</body>
</html>